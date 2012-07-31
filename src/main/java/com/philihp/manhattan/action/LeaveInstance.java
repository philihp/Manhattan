package com.philihp.manhattan.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.CreateInstanceForm;
import com.philihp.manhattan.form.InstanceForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;
import com.philihp.manhattan.model.PlayerCount;
import com.philihp.manhattan.model.TimeUnits;
import com.philihp.manhattan.util.ManhattanException;

public class LeaveInstance extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		InstanceForm form = (InstanceForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Instance instance = em.find(Instance.class, form.getInstanceId());
		
		if(instance == null) throw new ManhattanException("Could not find instance "+form.getInstanceId());
		if(user == null) throw new ManhattanException("Not logged in.");
		
		if(user.equals(instance.getPlayer5().getUser())) {
			instance.setPlayer5(new Instance.Player());
		}
		if(user.equals(instance.getPlayer4().getUser())) {
			instance.setPlayer4(instance.getPlayer5());
			instance.setPlayer5(new Instance.Player());
		}
		if(user.equals(instance.getPlayer3().getUser())) {
			instance.setPlayer3(instance.getPlayer4());
			instance.setPlayer4(instance.getPlayer5());
			instance.setPlayer5(new Instance.Player());
		}
		if(user.equals(instance.getPlayer2().getUser())) {
			instance.setPlayer2(instance.getPlayer3());
			instance.setPlayer3(instance.getPlayer4());
			instance.setPlayer4(instance.getPlayer5());
			instance.setPlayer5(new Instance.Player());
		}
		if(user.equals(instance.getPlayer1().getUser())) {
			instance.setPlayer1(instance.getPlayer2());
			instance.setPlayer2(instance.getPlayer3());
			instance.setPlayer3(instance.getPlayer4());
			instance.setPlayer4(instance.getPlayer5());
			instance.setPlayer5(new Instance.Player());
		}
		
		em.persist(instance);

		return mapping.findForward("root");
	}

}
