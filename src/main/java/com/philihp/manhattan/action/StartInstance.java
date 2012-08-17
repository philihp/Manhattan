package com.philihp.manhattan.action;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.manhattan.form.InstanceForm;
import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.Instance.Player;
import com.philihp.manhattan.jpa.User;
import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;

public class StartInstance extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		InstanceForm form = (InstanceForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Instance instance = em.find(Instance.class, form.getInstanceId());
		
		int count = instance.getPlayerCount();
		if(count < instance.getMinPlayers()) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("instance.minplayers", new int[] {instance.getMinPlayers(), instance.getPlayerCount()}));
			saveMessages(request, messages);
			return mapping.findForward("error");
		}
		else if(count > instance.getMaxPlayers()) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("instance.maxplayers", new int[] {instance.getMaxPlayers(), instance.getPlayerCount()}));
			saveMessages(request, messages);
			return mapping.findForward("error");
		}
		else {
			instance.setStatus(InstanceStatus.IN_PROGRESS);
			List<PlayerColor> randomColors = instance.getAvailableColors();
			Collections.shuffle(randomColors);
			for(Player player : instance.getPlayers()) {
				if(player.getColor() == null && player.getUser() != null) {
					player.setColor(randomColors.remove(0));
				}
			}
			return attachParam(mapping.findForward("view"), "instanceId", instance.getInstanceId());
		}
		
	}
	
	private ActionForward attachParam(ActionForward src, String paramName, int paramValue) {
		ActionForward dst = new ActionForward();
		dst.setRedirect(src.getRedirect());
		dst.setPath(src.getPath()+"?"+paramName+"="+paramValue);
		return dst;
	}

}
