package com.philihp.manhattan.mvc;

import static com.philihp.manhattan.util.Global.ATTR_OAUTH_ACCESS_TOKEN;
import static com.philihp.manhattan.util.Global.ATTR_OAUTH_REQUEST_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.philihp.manhattan.auth.OAuthServiceProvider;
import com.philihp.manhattan.domain.User;
import com.philihp.manhattan.repo.UserDao;
import com.philihp.manhattan.util.Global;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	@Qualifier("facebookServiceProvider")
	private OAuthServiceProvider facebookServiceProvider;

	@Autowired
	@Qualifier("twitterServiceProvider")
	private OAuthServiceProvider twitterServiceProvider;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/register")
    public String register(WebRequest request) {
    	return "register";
    }

	@RequestMapping(value={"/twitter"}, params="!oauth_token")
	public String twitterLogin(WebRequest request) {
		Token accessToken = (Token)request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		Token requestToken = (Token)request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		if(accessToken == null || requestToken == null) {
			OAuthService service = twitterServiceProvider.getService();
			requestToken = service.getRequestToken();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, requestToken, SCOPE_SESSION);
			
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value={"/twitter"}, params="oauth_token")
	public String twitterCallback(
			@RequestParam(value="oauth_token", required=false) String oauthToken,
			@RequestParam(value="oauth_verifier", required=false) String oauthVerifier,
			WebRequest request) {
		OAuthService service = twitterServiceProvider.getService();
		Token requestToken = (Token)request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);
		
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);
		
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.json");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		
		try {
			Map<String, Object> data = Global.mapper.readValue(oauthResponse.getStream(), new TypeReference<Map<String,Object>>() {});
			User user = userDao.findByTwitterId((Integer)data.get("id"));
			
			System.out.println(data.get("id"));
			System.out.println(user);
			
			request.setAttribute("user", user, SCOPE_SESSION);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}

		return "redirect:/";
	}
	
	@RequestMapping(value={"/facebook"}, params="!code")
	public String facebookLogin(WebRequest request) {
		
		Token accessToken = (Token)request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if(accessToken == null) {
			OAuthService service = facebookServiceProvider.getService();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, null, SCOPE_SESSION);
			
			return "redirect:" + service.getAuthorizationUrl(null);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value={"/facebook"}, params="code")
	public String facebookCallback(@RequestParam(value="code", required=false) String oauthVerifier, WebRequest request) {
		OAuthService service = facebookServiceProvider.getService();
		Token requestToken = (Token)request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);
		
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);
		
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		System.out.println(oauthResponse.getBody());
		
		return "redirect:/";
	}
	
}
