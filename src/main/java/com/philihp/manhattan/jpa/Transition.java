package com.philihp.manhattan.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "Transition")
@Table(name = "transition")
@Access(FIELD)
public class Transition extends BasicEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "transition_id")
	private int moveId;

	@Column(name = "command", nullable = false)
	private String command;
	
	@Column(name = "long_move")
	private boolean longMove;
	
	@ManyToOne(targetEntity = Instance.class)
	@JoinColumn(name = "instance_id", referencedColumnName = "instance_id")
	private Instance instance;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")  
	private User maker;

	public int getMoveId() {
		return moveId;
	}

	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean isLongMove() {
		return longMove;
	}

	public void setLongMove(boolean longMove) {
		this.longMove = longMove;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public User getMaker() {
		return maker;
	}

	public void setMaker(User maker) {
		this.maker = maker;
	}

}
