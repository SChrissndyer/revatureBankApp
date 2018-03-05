package com.revature.bankProject.admin;

import java.io.Serializable;

public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3990581332320034457L;
	private String name;
	private String userName;
	private String password;
	
	public Admin(String name ,String uName,String password){
		this.name=name;
		this.userName=uName;
		this.password=password;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	
	
	
	
	
}
