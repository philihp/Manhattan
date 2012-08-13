package com.philihp.manhattan.model;

public class Worker {
	private PlayerColor color;
	private WorkerType type;

	protected Worker(PlayerColor color, WorkerType type) {
		this.color = color;
		this.type = type;
	}

	public PlayerColor getColor() {
		return color;
	}

	public WorkerType getType() {
		return type;
	}
	
	public String toString() {
		return "W{"+type+":"+color+"}";
	}
	
}
