package com.revature.bankProject.users;

import java.util.ArrayList;

import com.revature.bankProject.users.accounts.Account;

public class Users {
	
	private String name;
	private String birthDay;
	private String userName;
	private String password;
	private boolean active;
	private int request;

	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Users(String name,String birthDay,String userName ,String password ,boolean active ) {
		setName(name);
		setBirthDay(birthDay);
		setUserName(userName);
		setPassword(password);
		setActive(active);
		
		
		
		
	}
	
	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public void setActive(boolean in) {
		active=in;
		
	}
	public boolean getActive() {
		return active;
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
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

	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Account a) {
		this.accounts.add(a);
	}

}
