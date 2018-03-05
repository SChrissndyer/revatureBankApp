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
	private boolean active=false;
	
	 public  Account(int ammount) {
		 this.ammount=ammount;
		 Random rand = new Random();
		 setNumeber(rand.nextInt(89999999)+10000000);
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
