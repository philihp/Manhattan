package com.philihp.manhattan.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.ImpersonateForm;
import com.philihp.manhattan.jpa.User;

public class Impersonate extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		ImpersonateForm form = (ImpersonateForm)actionForm;
		
		EntityManager em = (EntityManager)request.getAttribute("em");
		user = em.find(User.class, form.getUserId());
		
		request.getSession().setAttribute(BaseAction.USER, user);
		
		return mapping.findForward("root");
	}
}
