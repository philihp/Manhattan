package com.philihp.manhattan.action;

import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philihp.manhattan.jpa.User;


abstract public class BaseAction extends Action {
	
	public static String USER = "USER";
	
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
