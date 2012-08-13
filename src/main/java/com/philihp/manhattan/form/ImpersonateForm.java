package com.philihp.manhattan.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.model.PlayerColor;
import com.philihp.manhattan.model.TimeUnits;

public class ImpersonateForm extends ActionForm {
	
	private int userId;
	
	public ImpersonateForm() {
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		userId = 0;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
