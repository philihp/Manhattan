package com.philihp.manhattan.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.MakeMoveForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.Transition;
import com.philihp.manhattan.jpa.User;

public class CommitMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		MakeMoveForm form = (MakeMoveForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Instance instance = em.find(Instance.class, form.getInstanceId());

		Transition newMove = new Transition();
		newMove.setInstance(instance);
		newMove.setCommand(form.getCommand());
		
		em.persist(newMove);

		return attachParam(mapping.findForward("view"),"instanceId",instance.getInstanceId());
	}
	
	private ActionForward attachParam(ActionForward src, String paramName, int paramValue) {
		ActionForward dst = new ActionForward();
		dst.setRedirect(src.getRedirect());
		dst.setPath(src.getPath()+"?"+paramName+"="+paramValue);
		return dst;
	}

}
