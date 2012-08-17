package com.philihp.manhattan.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MakeMoveForm extends ActionForm {

	private int instanceId;
	private String command;

	public MakeMoveForm() {
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		instanceId = 0;
		command = null;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
