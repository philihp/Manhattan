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

@Entity(name = "User")
@Table(name = "user")
@Access(FIELD)
@NamedQueries({
		@NamedQuery(name = "findUserWithFacebook", query = "SELECT u FROM User u WHERE u.facebook.id = :facebook_id"),
		@NamedQuery(name = "findUserWithTwitter", query = "SELECT u FROM User u WHERE u.twitter.id = :twitter_id") })
public class User extends BasicEntity implements Serializable {

	@Embeddable
	@Access(FIELD)
	public static class Twitter implements Serializable {

		@Basic
		@Column(name = "twitter_id")
		private int id;

		@Basic
		@Column(name = "twitter_access_token")
		private String access_token;

		@Basic
		@Column(name = "twitter_access_secret")
		private String access_secret;

		@Basic
		@Column(name = "twitter_name")
		private String name;

		@Basic
		@Column(name = "twitter_screen_name")
		private String screen_name;

		@Basic
		@Column(name = "twitter_profile_image_url")
		private String profile_image_url;

		@Transient
		private String error;

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

		public String getAccess_secret() {
			return access_secret;
		}

		public void setAccess_secret(String access_secret) {
			this.access_secret = access_secret;
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

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

	}

	@Embeddable
	@Access(FIELD)
	public static class Facebook implements Serializable {
		
		public static class Error implements Serializable {
			private String message;
			
			public Error() {
			}

			public String getMessage() {
				return message;
			}

			public void setMessage(String message) {
				this.message = message;
			}
			
		}

		@Basic
		@Column(name = "facebook_id")
		private String id;
		
		@Basic
		@Column(name = "facebook_access_token")
		private String access_token;

		@Basic
		@Column(name = "facebook_name")
		private String name;

		@Basic
		@Column(name = "facebook_username")
		private String username;

		@Transient
		private Error error;

		public Facebook() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
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

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Error getError() {
			return error;
		}

		public void setError(Error error) {
			this.error = error;
		}

	}

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Embedded
	private Twitter twitter;

	@Embedded
	private Facebook facebook;

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

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
