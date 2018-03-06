package com.revature.bankProject;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.bankProject.admin.Admin;
import com.revature.bankProject.employee.Employee;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;

public class Bank implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6562931097564644891L;
	private  ArrayList<Account> accounts = new ArrayList<Account>();
	private  ArrayList<Users> users = new ArrayList<Users>();
	private  ArrayList<Employee> employees = new ArrayList<Employee>();
	private  ArrayList<Admin> admins = new ArrayList<Admin>();
	public static Users loggedinU;
	
	public Bank() {
		
        
		
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	

	public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}

	public static Users getLoggedinU() {
		return loggedinU;
	}

	public void setLoggedinU(Users loggedinU) {
		Bank.loggedinU = loggedinU;
	}

	public void addaccount() {
		Account c=new Account(0);
		accounts.add(c);
	}
	public void setloggedinU(String a) {
		for(Users p:users) {
			if(p.getUserName().equals(a)) {
				loggedinU=p;
			}
		}
		
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
	
	public void approver(String s,boolean a) {
		for(Users m:users) {
			if(m.getUserName().equals(s)) {
				loggedinU=m;
				
				
			}}
		loggedinU.setActive(a);
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
	public boolean loginEmployeeName(String s){
		boolean temp = true;
		for(Employee m:employees) {
    		if( m.getUserName().equals(s)) {
    			temp=false;
    		}
    		
    	}
		return temp;
	}
	
	public boolean loginEmployeepassword(String ss,String s) {
		boolean temp = true;
		for(Employee m:employees) {
			if( m.getPassword().equals(s)&&m.getUserName().equals(ss)) {
				temp=false;
			}
			
		}
		return temp;
	}
	public boolean loginAdminName(String s){
		boolean temp = true;
		for(Admin m:admins) {
    		if( m.getUserName().equals(s)) {
    			temp=false;
    		}
    		
    	}
		return temp;
	}
	
	public boolean loginAdminpassword(String ss,String s) {
		boolean temp = true;
		for(Admin m:admins) {
			if( m.getPassword().equals(s)&&m.getUserName().equals(ss)) {
				temp=false;
			}
			
		}
		return temp;
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
	public void findUser(String user) {
		for (Users p:users) {
			if(p.getUserName().equals(user)) {
				loggedinU=p;
			}
		}
		
	}
	public String userInfo(String user) {
		String s="";
		for (Users p:users) {
			if(p.getUserName().equals(user)) {
				
				loggedinU=p;
				s+=(p.getName()+"\n");
				s+=(p.getBirthDay()+"\n");
				s+=(getaccounts());
			}
		}

		return s;
	}
	public String getAllInfo() {
		String s="";
		for(Users a:users) {
			s+=a.getName()+" "+a.getBirthDay()+"\n";
			for(Account p:a.getAccounts()) {
				s+=p.getNumeber()+" "+p.getAmmount()+"\n";
			}
			
		}
		s+="And now for all acounts \n \n";
		for (Account b:accounts) {
			s+=b.getNumeber()+" "+b.getAmmount()+"\n";
		}
		return s;
		
	}
	public boolean hasaccreq(String a) {
		for (Users p:users) {
			if (p.getUserName().equals(a)){
				if(p.getRequest()==0) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		
		
		
		return false;
		
	}
	public void linker(String a) {
		for(Users p: users) {
			if(p.getUserName().equals(a)) {
				for (Account q: accounts) {
					if(p.getRequest()==q.getNumeber()) {
						p.setAccounts(q);
						p.setRequest(0);
					}
				}
			}
		}
	}
}
