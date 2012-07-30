package com.philihp.manhattan.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.philihp.manhattan.model.PlayerColor;
import com.philihp.manhattan.model.TimeUnits;

public class CreateInstanceForm extends ActionForm {
	private String name;
	private int minPlayers;
	private int maxPlayers;
	private int timeLimitA;
	private String timeLimitAUnits;
	private String myColor;

	public CreateInstanceForm() {
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		name = null;
		minPlayers = 2;
		maxPlayers = 5;
		timeLimitA = 24;
		myColor = null;
		timeLimitAUnits = "HOURS";
	}

	public String getTimeLimitAUnits() {
		return timeLimitAUnits;
	}

	public void setTimeLimitAUnits(String units) {
		this.timeLimitAUnits = units;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getTimeLimitA() {
		return timeLimitA;
	}

	public void setTimeLimitA(int timeLimitAMinutes) {
		this.timeLimitA = timeLimitAMinutes;
	}

	public String getMyColor() {
		return myColor;
	}

	public void setMyColor(String myColor) {
		this.myColor = myColor;
	}

}
