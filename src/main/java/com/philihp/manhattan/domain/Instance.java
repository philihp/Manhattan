package com.philihp.manhattan.domain;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;

@Entity
public class Instance extends BasicEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column
	private int id;

	@Column
	private String name;

	@Column
	private int minPlayers;

	@Column
	private int maxPlayers;

	@Column
	private int timeLimitAMinutes;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private InstanceStatus status;

	@Column(name = "seed")
	private long seed;

	@OneToMany(targetEntity = Transition.class, mappedBy = "instance")
	@OrderBy
	private List<Transition> transitions;

	public Instance() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTimeLimitAMinutes() {
		return timeLimitAMinutes;
	}

	public void setTimeLimitAMinutes(int timeLimitAMinutes) {
		this.timeLimitAMinutes = timeLimitAMinutes;
	}

	public InstanceStatus getStatus() {
		return status;
	}

	public void setStatus(InstanceStatus status) {
		this.status = status;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}


	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setMoves(List<Transition> moves) {
		this.transitions = moves;
	}

}
