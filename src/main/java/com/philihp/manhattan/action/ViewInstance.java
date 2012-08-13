package com.philihp.manhattan.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.InstanceForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.Transition;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.model.MainBoard;
import com.philihp.manhattan.model.Move;
import com.philihp.manhattan.model.PlayerColor;

public class ViewInstance extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		InstanceForm form = (InstanceForm) actionForm;
		EntityManager em = (EntityManager) request.getAttribute("em");

		Instance instance = em.find(Instance.class, form.getInstanceId());
		request.setAttribute("instance", instance);
		request.setAttribute("colors", PlayerColor.values());
		
		
		
		MainBoard board = new MainBoard(instance);
		for(Transition transition: instance.getTransitions()) {
			board.applyTurn(Move.parse(transition.getCommand()));
		}
		
		
		
		request.setAttribute("board", board);
		

		switch (instance.getStatus()) {
		case RECRUITING:
			return mapping.findForward("recruiting");
		case IN_PROGRESS:
			return mapping.findForward("in-progress");
		case REPLACING:
			return mapping.findForward("replacing");
		case FINISHED:
			return mapping.findForward("finished");
		default:
			throw new RuntimeException("Unrecognized Instance Status "
					+ instance.getStatus());
		}

	}

}
