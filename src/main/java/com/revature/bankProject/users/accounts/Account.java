package com.revature.bankProject.users.accounts;

import java.io.Serializable;
import java.util.Random;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1168184147635516989L;
	private int numeber ;
	private double ammount;
	private int active=0;
	
	 public  Account(int ammount) {
		 this.ammount=ammount;
		 Random rand = new Random();
		 setNumeber(rand.nextInt(89999999)+10000000);
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumeber() {
		return numeber;
	}

	public void setNumeber(int numeber) {
		this.numeber = numeber;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	
}
