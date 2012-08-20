package com.philihp.manhattan.domain;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
public class Config implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

	@Column
	private String value;

	public Config() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
