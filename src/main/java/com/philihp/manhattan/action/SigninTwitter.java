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
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.jpa.User.Twitter;

public class SigninTwitter extends BaseAction {
	
	private static final String REQUEST_TOKEN = "REQUEST_TOKEN";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		String clientId = (String)getServlet().getServletContext().getAttribute("twitter_id");
		String clientSecret = (String)getServlet().getServletContext().getAttribute("twitter_secret");
		String callback = (String)getServlet().getServletContext().getAttribute("twitter_callback");
		
		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey(clientId)
				.apiSecret(clientSecret)
				.callback(callback)
				.build();
		
		String verifier = request.getParameter("oauth_verifier");
		
		
		if(verifier == null) {
			Token requestToken = service.getRequestToken();
			request.getSession().setAttribute(REQUEST_TOKEN, requestToken);
			String authURL = service.getAuthorizationUrl(requestToken);
			return new ActionForward(authURL, true);
		}
		else {
			Token requestToken = (Token)request.getSession().getAttribute(REQUEST_TOKEN);
			if(requestToken == null) throw new Exception("OAuth Request Token is missing. This is likely because your browser's session wasn't setup yet, but it should be now. Try logging in again.");
			
			Token accessToken = service.getAccessToken(requestToken,new Verifier(verifier));
			OAuthRequest authRequest = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.json");
			service.signRequest(accessToken, authRequest);
			
			Response authResponse = authRequest.send();
			
			Gson gson = new Gson();
			Twitter twitter = gson.fromJson(authResponse.getBody(), Twitter.class);
			
			EntityManager em = (EntityManager)request.getAttribute("em");
			TypedQuery<User> query = em.createNamedQuery("findUserWithTwitter", User.class);
			query.setParameter("twitter_id", twitter.getId());
			List<User> results = query.getResultList();
			if(results.size() == 0) {
				user = new User();
				user.setTwitter(twitter);
				user.setName(twitter.getName());
				twitter.setAccess_token(accessToken.getToken());
				em.persist(user);
				
				request.getSession().setAttribute(BaseAction.USER, user);
			}
			else {
				user = results.get(0);
				user.getTwitter().setAccess_token(accessToken.getToken());
				request.getSession().setAttribute(BaseAction.USER, user);
			}
			
			return mapping.findForward("root");
		}
	}
}
