package com.philihp.manhattan.mvc;

import static com.philihp.manhattan.util.Global.ATTR_OAUTH_ACCESS_TOKEN;
import static com.philihp.manhattan.util.Global.ATTR_OAUTH_REQUEST_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.philihp.manhattan.auth.OAuthServiceProvider;

@Controller
public class FacebookController {
	
	@Autowired
	@Qualifier("facebookServiceProvider")
	private OAuthServiceProvider facebookServiceProvider;
	
	private static final Token REQUEST_TOKEN = null;
	
	@RequestMapping(value={"/login-facebook"}, method=RequestMethod.GET, params="!code")
	public String login(WebRequest request) {
		
		Token accessToken = (Token)request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if(accessToken == null) {
			OAuthService service = facebookServiceProvider.getService();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, REQUEST_TOKEN, SCOPE_SESSION);
			
			return "redirect:" + service.getAuthorizationUrl(REQUEST_TOKEN);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value={"/login-facebook"}, method=RequestMethod.GET, params="code")
	public ModelAndView callback(@RequestParam(value="code", required=false) String oauthVerifier, WebRequest request) {
		OAuthService service = facebookServiceProvider.getService();
		Token requestToken = (Token)request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);
		
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);
		
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		System.out.println(oauthResponse.getBody());
		
		return  new ModelAndView("redirect:/");
	}
}
