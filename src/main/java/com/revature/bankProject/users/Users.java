package com.revature.bankProject.users;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.bankProject.users.accounts.Account;

public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3304309369794438711L;
	private String name;
	private String birthDay;
	private String userName;
	private String password;
	private int active;
	private int request=0;

	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Users(String name,String birthDay,String userName ,String password ,int active ) {
		setName(name);
		setBirthDay(birthDay);
		setUserName(userName);
		setPassword(password);
		setActive(active);
		Account a=new Account(0);
		accounts.add(a);
	}
	
	public Users() {
		super();
	}

	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public void setActive(int in) {
		this.active=in;
		
	}
	public int getActive() {
		return active;
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDay() {
		return birthDay.format(birthDay, "DD-MM-YYYY");
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay.format(birthDay, "DD-MM-YYYY");
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
	public void setAllAccounts(ArrayList<Account> a) {
		this.accounts=a;
	}
	
	public void setAccounts(Account a) {
		this.accounts.add(a);
	}

	

}
