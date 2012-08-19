package com.philihp.manhattan.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.User;

public class Main extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		request.setAttribute("myInstances", findMyInstances(em, user));
		request.setAttribute("instancesRecruiting", findRecruitingInstances(em, user));
		
		return mapping.findForward("main");
	}

	private List<Instance> findRecruitingInstances(EntityManager em, User user) {
		if(user == null) user = new User();
		
		TypedQuery<Instance> query = em.createNamedQuery("findRecruitingInstances", Instance.class);
		query.setParameter("user", user);
		List<Instance> instancesRecruiting = query.getResultList();
		return instancesRecruiting;
	}

	private List<Instance> findMyInstances(EntityManager em, User user) {
		if(user == null) return new ArrayList<Instance>();
		
		TypedQuery<Instance> query = em.createNamedQuery("findMyInstances", Instance.class);
		query.setParameter("user", user);
		List<Instance> myInstances = query.getResultList();
		return myInstances;
	}

}
