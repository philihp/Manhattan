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

public class JoinInstance extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		InstanceForm form = (InstanceForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Instance instance = em.find(Instance.class, form.getInstanceId());
		if(instance.getPlayerCount() == 5) {
			throw new RuntimeException("Can not join instance "+instance.getInstanceId()+", it already has 5 players.");
		}
		
		Instance.Player player = instance.getNextOpenPlayerSpot();
		player.setUser(user);
		if(form.getColor() == null || "".equals(form.getColor()))
			player.setColor(null);
		else
			player.setColor(PlayerColor.valueOf(form.getColor()));
		
		em.persist(instance);

		return mapping.findForward("root");
	}

}
