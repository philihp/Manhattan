package com.philihp.manhattan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BasicEntity implements Serializable {

	@Column
	private Date dateCreated;

	@Column
	private Date dateUpdated;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		Date now = new Date();
		setDateUpdated(now);
		if (getDateCreated() == null) {
			setDateCreated(now);
		}
	}

}
