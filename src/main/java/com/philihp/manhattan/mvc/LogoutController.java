package com.philihp.manhattan.mvc;

import static com.philihp.manhattan.util.Global.ATTR_OAUTH_ACCESS_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@RequestMapping(value={"/twitter"})
	public String logout(WebRequest request) {
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, null, SCOPE_SESSION);
		return "redirect:/";
	}
    

}
