package com.philihp.manhattan.model;

public enum InstanceStatus {
	/* has not started, looking for players */
	RECRUITING,
	
	/* has started, but a player left. looking to replace him. */
	REPLACING,
	
	/* currently in progress */
	IN_PROGRESS,
	
	/* game has ended */
	FINISHED;
}
