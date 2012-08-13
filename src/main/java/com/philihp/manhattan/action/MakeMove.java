package com.philihp.manhattan.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.CreateInstanceForm;
import com.philihp.manhattan.form.InstanceForm;
import com.philihp.manhattan.form.MakeMoveForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.Transition;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;
import com.philihp.manhattan.model.PlayerCount;
import com.philihp.manhattan.model.TimeUnits;

public class MakeMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		MakeMoveForm form = (MakeMoveForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Instance instance = em.find(Instance.class, form.getInstanceId());
		request.setAttribute("instance", instance);

		//try doing new move.
		
		//save new board to view
		return mapping.findForward("confirm");
	}

}
