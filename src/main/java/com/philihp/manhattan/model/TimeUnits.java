package com.philihp.manhattan.model;

public enum TimeUnits {
	DAYS(60*24),
	HOURS(60),
	MINUTES(1);
	
	private int minutes;
	private TimeUnits(int minutes) {
		this.minutes = minutes;
	}
	public int getMinutesInUnit() {
		return this.minutes;
	}
	public String getLabel() {
		return this.toString().substring(0,1) + this.toString().substring(1).toLowerCase();
	}
	public String getValue() {
		return this.toString();
	}
}
