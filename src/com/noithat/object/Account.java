package com.noithat.object;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="account")
public class Account implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7245963939169737508L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "userName")
	@Type(type="text")
	private String userName;
	
	@Column(name = "password")
	@Type(type="text")
	private String password;
	
	@Column(name = "serialNumber")
	@Type(type="text")
	private String serialNumber;
	
	@Column(name = "permission")
	private int permission;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
}
