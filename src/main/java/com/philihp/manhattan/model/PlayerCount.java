package com.philihp.manhattan.model;

public enum PlayerCount {
	
	TWO, THREE, FOUR, FIVE;

	public int asNumber() {
		return ordinal()+2;
	}

	public String getLabel() {
		return Integer.toString(asNumber());
	}
	public String getValue() {
		return Integer.toString(asNumber());
	}
}
