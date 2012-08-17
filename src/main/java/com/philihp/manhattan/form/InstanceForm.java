package com.philihp.manhattan.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InstanceForm extends ActionForm {
	private int instanceId;
	private String color;

	public InstanceForm() {
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		instanceId = 0;
		color = null;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public String getColor() {
		return color;
	}
	

	public void setColor(String color) {
		this.color = color;
	}

}
