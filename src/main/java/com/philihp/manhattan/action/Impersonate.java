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
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.philihp.manhattan.form.ImpersonateForm;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.jpa.User.Twitter;

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
