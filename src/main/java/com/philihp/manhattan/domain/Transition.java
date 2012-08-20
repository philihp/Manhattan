package com.philihp.manhattan.domain;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Transition extends BasicEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column
	private int id;

	@Column
	private String command;
	
	@ManyToOne(targetEntity = Instance.class)
	@JoinColumn(referencedColumnName = "id")
	private Instance instance;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(referencedColumnName = "id")  
	private User maker;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
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
