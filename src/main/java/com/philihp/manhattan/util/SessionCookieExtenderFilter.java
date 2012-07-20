package com.philihp.manhattan.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Filter will overwrite the JSESSIONID session cookie with a new
 * session cookie, with the same value, but with an Expires time 2 weeks from
 * now. Normally the session cookie expires when the session ends (even though
 * we tell the servlet container to keep sessions around for 2 weeks).
 * 
 * This is fixed in Servlet 3.0 with an option, but we're in 2.5 right now.
 */
public class SessionCookieExtenderFilter implements Filter {
	
	private String contextPath;

	public SessionCookieExtenderFilter() {
	}

	public void init(FilterConfig config) throws ServletException {
		contextPath = config.getServletContext().getContextPath();
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		String sessionId = request.getSession().getId();
		long expireTimestamp = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7 * 2);
		String expireDate = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z").format(new Date(expireTimestamp));
		response.setHeader("Set-Cookie", String.format("JSESSIONID=%s;Expires=%s;Path=%s", sessionId, expireDate, contextPath));
		
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
