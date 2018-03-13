package com.revature.bankProject.Application;

import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;

public class Application {
	private String u;
	private int a;
	private int answer=-1;
	public  Application(){
		
		
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}

}
