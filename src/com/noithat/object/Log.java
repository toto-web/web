package com.noithat.object;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Log")
public class Log implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2354063843120232882L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "userId")
	@Type(type="text")
	private String userId;
	
	@Column(name = "timeLogin")
	private Timestamp timeLogin;
	
	@Column(name = "timeLogout")
	private Timestamp timeLogout;
	
	@Column(name = "action")
	@Type(type="text")
	private String action;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTimeLogin() {
		return timeLogin;
	}

	public void setTimeLogin(Timestamp timeLogin) {
		this.timeLogin = timeLogin;
	}

	public Timestamp getTimeLogout() {
		return timeLogout;
	}

	public void setTimeLogout(Timestamp timeLogout) {
		this.timeLogout = timeLogout;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
