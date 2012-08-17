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

public class SigninFacebook extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		String clientId = (String)getServlet().getServletContext().getAttribute("facebook_id");
		String clientSecret = (String)getServlet().getServletContext().getAttribute("facebook_secret");
		String callback = (String)getServlet().getServletContext().getAttribute("facebook_callback");
		
		OAuthService service = new ServiceBuilder().provider(FacebookApi.class)
				.apiKey(clientId).apiSecret(clientSecret)
				.callback(callback).build();
		
		Token accessToken = null;
		if(user != null && user.getFacebook() != null && user.getFacebook().getAccess_token() != null) {
			accessToken = new Token(user.getFacebook().getAccess_token(),"");
		}
		
		if(accessToken == null) {
			String verifier = request.getParameter("code");
			if(verifier == null) {
				String authURL = service.getAuthorizationUrl(null);
				return new ActionForward(authURL, true);
			}
			else {
				accessToken = service.getAccessToken((Token)null, new Verifier(verifier));
			}
		}
		
		OAuthRequest authRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
		service.signRequest(accessToken, authRequest);
		Response authResponse = authRequest.send();
		
		Gson gson = new Gson();
		Facebook facebook = gson.fromJson(authResponse.getBody(), Facebook.class);
		if(facebook.getError() != null) {
			if(user.getFacebook().getAccess_token() != null) {
				user.getFacebook().setAccess_token(null);
				return mapping.findForward("reauth");
			}
			else {
				throw new OAuthException(facebook.getError().getMessage());
			}
		}

		EntityManager em = (EntityManager)request.getAttribute("em");
		if(user == null) {
			TypedQuery<User> query = em.createNamedQuery("findUserWithFacebook", User.class);
			query.setParameter("facebook_id", facebook.getId());
			List<User> results = query.getResultList();
			if(results.size() == 0) {
				user = new User();
			}
			else {
				user = results.get(0);
			}
		}

		facebook.setAccess_token(accessToken.getToken());
		user.setFacebook(facebook);
		user.setName(facebook.getName());
		em.persist(user);

		request.getSession().setAttribute(BaseAction.USER, user);

		return mapping.findForward("root");
	}
}
