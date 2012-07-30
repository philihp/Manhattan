package com.philihp.manhattan.model.building;

import com.philihp.manhattan.model.Board;
import com.philihp.manhattan.util.ManhattanException;

public abstract class Building {
	private int damage = 0;
	private String id;
	
	public Building(String id) {
		this.id = id;
	}
	
	/**
	 * @param option String that's either "a" or "b", or empty string if don't care.
	 */
	public abstract void use(Board board, String option) throws ManhattanException;
	
	public String toString() {
		return id;
	}
	
}
