package com.philihp.manhattan.model;

public enum PlayerColor {
	RED, YELLOW, GREEN, BLUE, PURPLE;

	public String getLabel() {
		return this.toString().substring(0,1) + this.toString().substring(1).toLowerCase();
	}
	public String getValue() {
		return this.toString();
	}
}
