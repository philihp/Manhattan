package com.philihp.manhattan.jpa;

import static javax.persistence.AccessType.FIELD;

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

@Entity(name = "User")
@Table(name = "user")
@Access(FIELD)
@NamedQueries({
	//@NamedQuery(name = "findUserWithFacebook", query = "SELECT u FROM User u WHERE u.facebook.id = :facebook_id"),
	@NamedQuery(name = "findUserWithTwitter", query = "SELECT u FROM User u WHERE u.twitter.id = :twitter_id")
})
public class User extends BasicEntity {
	
	@Embeddable
	@Access(FIELD)
	public static class Twitter {
		
		@Basic
		@Column(name="twitter_id")
		private int id;
		
		@Transient
		private String access_token;
		
		@Basic
		@Column(name="twitter_name")
		private String name;
		
		@Basic
		@Column(name="twitter_screen_name")
		private String screen_name;
		
		@Basic
		@Column(name="twitter_profile_image_url")
		private String profile_image_url;
	
		public Twitter() {
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
		
		public String getAccess_token() {
			return access_token;
		}
		
		public void setAccess_token(String access_token) {
			this.access_token = access_token; 
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getScreen_name() {
			return screen_name;
		}
	
		public void setScreen_name(String screen_name) {
			this.screen_name = screen_name;
		}
	
		public String getProfile_image_url() {
			return profile_image_url;
		}
	
		public void setProfile_image_url(String profile_image_url) {
			this.profile_image_url = profile_image_url;
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Embedded
	private Twitter twitter;

	@Column(name = "name", nullable = false)
	private String name;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
