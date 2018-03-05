package com.revature.bankProject;

import java.util.ArrayList;

import com.revature.bankProject.admin.Admin;
import com.revature.bankProject.employee.Employee;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;

public class Bank {
	
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<Users> users = new ArrayList<Users>();
	private static ArrayList<Employee> employees = new ArrayList<Employee>();
	private static ArrayList<Admin> admins = new ArrayList<Admin>();
	private static Users loggedinU;
	private static Users loggedinE;
	private static Users loggedinA;
	public Bank() {
		Users u =new Users( "Bill", "01-01-1990", "tester1" , "password" , true);
		Account a=new Account(5000);
		Account b=new Account(400);
		u.setAccounts(a);
		u.setAccounts(b);
		accounts.add(a);accounts.add(b);
        users.add(u);
		
	}
	public void setloggedinU() {
		
		loggedinU=null;
		
	}
	public boolean seenActive() {
		
		return loggedinU.getActive();
		
	}
	public double getamount(int from) {
		ArrayList<Account> p = loggedinU.getAccounts();
	for (Account m:p) {
		if(m.getNumeber()==from) {
			return m.getAmmount();
		}
	}
		
		return 0;
		
	}
	public String getUser() {
		return loggedinU.getName();
		
	}
	
	public boolean loginUserName(String s){
		boolean temp = true;
		for(Users m:users) {
    		if( m.getUserName().equals(s)) {
    			temp=false;
    		}
    		
    	}
		return temp;
	}
	
	public boolean loginUserpassword(String ss,String s) {
		boolean temp = true;
		for(Users m:users) {
			if( m.getPassword().equals(s)&&m.getUserName().equals(ss)) {
				loggedinU=m;
				temp=false;
			}
			
		}
		return temp;
	}
	public void usercheck(String s) {
		
	}
	public String adduser(String name,String dOB,String userName ,String password) {
		Users u =new Users( name, dOB, userName , password , false);
        users.add(u);
        return u.getUserName();
	}
	
	public String getaccounts() {
		String s="";
		ArrayList<Account> p = loggedinU.getAccounts();
		for (Account m:p) {
			s+=("account number "+m.getNumeber()+" and balance "+m.getAmmount()+"\n");
		}
		return s;
	}
	public void withdraw(double amount,int from ) {
		ArrayList<Account> temp = loggedinU.getAccounts();
		for (Account p:temp) {
			if(p.getNumeber()==from) {
				p.setAmmount(p.getAmmount()-amount);
			}
		}
		
	}
	public boolean accountCheck(int from) {
		ArrayList<Account> p = loggedinU.getAccounts();
		for (Account m:p) {
			if(m.getNumeber()==from) {
				return false;
			}
		}
		return true;
	}
	public boolean accountChecks(int from) {
		
		for (Account m:accounts) {
			if(m.getNumeber()==from) {
				return false;
			}
		}
		return true;
	}
	public void setRequest(int a) {
		loggedinU.setRequest(a);
	}
	
	public void deposit(float amount, int from) {
		ArrayList<Account> temp = loggedinU.getAccounts();
		for (Account p:temp) {
			if(p.getNumeber()==from) {
				p.setAmmount(p.getAmmount()+amount);
			}
		}
		
	}
	public void trasnfer(float amount, int from, int to) {
		withdraw(amount,from);
		deposit(amount,to);
		
	}

}
