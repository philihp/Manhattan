package com.philihp.manhattan.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

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
