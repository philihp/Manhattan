package com.philihp.manhattan.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.jpa.User;


abstract public class BaseAction extends Action {
	
	public static String USER = "user";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Action: "+this.getClass().getCanonicalName());

		User user = reattachUser(request);
			
		return execute(mapping, actionForm, request, response, user);
	}

	abstract ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception;

	private User reattachUser(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute(USER);
		if(user != null) {
			EntityManager em = (EntityManager)request.getAttribute("em");
			user = em.find(User.class, user.getUserId());
			request.getSession().setAttribute(USER, user);
		}
		return user;
		
	}
	
}
