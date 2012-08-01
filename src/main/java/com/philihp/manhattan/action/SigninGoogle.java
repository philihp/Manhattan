package com.philihp.manhattan.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.GoogleApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.jpa.User.Facebook;
import com.philihp.manhattan.jpa.User.Twitter;

public class SigninGoogle extends BaseAction {

	private static final String REQUEST_TOKEN = "GOOGLE_REQUEST_TOKEN";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		String clientId = (String)getServlet().getServletContext().getAttribute("google_id");
		String clientSecret = (String)getServlet().getServletContext().getAttribute("google_secret");
		String clientScope = (String)getServlet().getServletContext().getAttribute("google_scope");
		String callback = (String)getServlet().getServletContext().getAttribute("google_callback");
		
		OAuthService service = new ServiceBuilder().provider(GoogleApi.class)
				.apiKey(clientId).apiSecret(clientSecret)
				.scope(clientScope)
				.callback(callback).build();

		
		String verifier = request.getParameter("oauth_verifier");
		
		Token accessToken = null;
//		if(user != null && user.getTwitter() != null && user.getTwitter().getAccess_token() != null) {
//			accessToken = new Token(user.getTwitter().getAccess_token(), user.getTwitter().getAccess_secret());
//		}
		
		if(accessToken == null) {
			if(verifier == null) {
				Token requestToken = service.getRequestToken();
				request.getSession().setAttribute(REQUEST_TOKEN, requestToken);
				System.out.println("Set Token: "+requestToken);
				String authURL = service.getAuthorizationUrl(requestToken);
				return new ActionForward(authURL, true);
			}
			else {
				Token requestToken = (Token)request.getSession().getAttribute(REQUEST_TOKEN);
				System.out.println("Get Token: "+requestToken);
				if(requestToken == null) throw new Exception("OAuth Request Token is missing. This is likely because your browser's session wasn't setup yet, but it should be now. Try logging in again.");
				accessToken = service.getAccessToken(requestToken,new Verifier(verifier));
			}
		}

		System.out.println("Access Token: "+accessToken);
		OAuthRequest authRequest = new OAuthRequest(Verb.GET, "https://www.googleapis.com/auth/v1/userinfo");
		service.signRequest(accessToken, authRequest);
	    authRequest.addHeader("GData-Version", "3.0");
		Response authResponse = authRequest.send();
		
		System.out.println(authResponse.getBody());
		
		Gson gson = new Gson();
		Twitter twitter = gson.fromJson(authResponse.getBody(), Twitter.class);
		
		if(twitter.getError() != null) {
			if(user.getTwitter().getAccess_token() != null) {
				user.getTwitter().setAccess_token(null);
				user.getTwitter().setAccess_secret(null);
				return mapping.findForward("reauth");
			}
			else {
				throw new OAuthException(twitter.getError());
			}
		}

		EntityManager em = (EntityManager)request.getAttribute("em");
		if(user == null) {
			TypedQuery<User> query = em.createNamedQuery("findUserWithTwitter", User.class);
			query.setParameter("twitter_id", twitter.getId());
			List<User> results = query.getResultList();
			if(results.size() == 0) {
				user = new User();
			}
			else {
				user = results.get(0);
			}
		}
		twitter.setAccess_token(accessToken.getToken());
		twitter.setAccess_secret(accessToken.getSecret());
		user.setTwitter(twitter);
		user.setName(twitter.getName());
		em.persist(user);
		
		request.getSession().setAttribute(BaseAction.USER, user);
		
		return mapping.findForward("root");
	}
}
