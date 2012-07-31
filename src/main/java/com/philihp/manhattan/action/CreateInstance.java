package com.philihp.manhattan.action;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.form.CreateInstanceForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;
import com.philihp.manhattan.model.PlayerCount;
import com.philihp.manhattan.model.TimeUnits;

public class CreateInstance extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		CreateInstanceForm form = (CreateInstanceForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");

		request.setAttribute("numPlayers", PlayerCount.values());
		request.setAttribute("timeUnits", TimeUnits.values());
		request.setAttribute("colors", PlayerColor.values());
		
		if(request.getParameter("name") == null) {
			return mapping.findForward("form");
		}
		
		Instance instance = new Instance();
		instance.setName(form.getName());
		instance.setMinPlayers(form.getMinPlayers());
		instance.setMaxPlayers(form.getMaxPlayers());
		instance.setTimeLimitAMinutes(form.getTimeLimitA() * TimeUnits.valueOf(form.getTimeLimitAUnits()).getMinutesInUnit());
		instance.setStatus(InstanceStatus.RECRUITING);
		instance.setSeed(new Random().nextLong());
		
		instance.setPlayer1(new Instance.Player());
		instance.getPlayer1().setUser(user);
		if(form.getMyColor() == null || form.getMyColor().equals(""))
			instance.getPlayer1().setColor(null);
		else
			instance.getPlayer1().setColor(PlayerColor.valueOf(form.getMyColor()));
		
		em.persist(instance);

		return mapping.findForward("root");
	}

}
