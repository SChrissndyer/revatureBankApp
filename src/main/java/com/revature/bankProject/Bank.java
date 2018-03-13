package com.revature.bankProject;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.bankProject.Application.Application;
import com.revature.bankProject.admin.Admin;
import com.revature.bankProject.employee.Employee;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountsDaoImpl;
import com.revature.dao.AdminDao;
import com.revature.dao.AdminDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.UsersDao;
import com.revature.dao.UsersDaoImpl;


public class Bank implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6562931097564644891L;
	private  ArrayList<Account> accounts = new ArrayList<Account>();
	private  ArrayList<Users> users = new ArrayList<Users>();
	private  ArrayList<Employee> employees = new ArrayList<Employee>();
	private  ArrayList<Admin> admins = new ArrayList<Admin>();
	private ArrayList<Application> applic=new ArrayList<Application>();
	public static Users loggedinU;
	
	public Bank() {
		super();
	}
		
	public void loadAccountsAll(){
		AccountDao ac = new AccountsDaoImpl();
		this.accounts=ac.retrieveAccountForAdmin();
		this.applic=ac.retrieveAccountForApp();
		UsersDao us = new UsersDaoImpl();
		this.users= us.retrieveAllUser();
		AdminDao ad = new AdminDaoImpl();
		this.admins= ad.retrieveAllUser();
		EmployeeDao add = new EmployeeDaoImpl();
		this.employees= add.retrieveAllEmployee();
		
		
		for( Users u:users) {
			
			u.setAllAccounts(ac.retrieveAccountForUser(u.getUserName()));
			
		}
		
		
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
		
		AccountDao us = new AccountsDaoImpl();
		us.createAccountPreparedStmt(c);
	}
	public void setloggedinU(String a) {
		for(Users p:users) {
			if(p.getUserName().equals(a)) {
				loggedinU=p;
			}
		}
		
	}
	public boolean seenActive() {
		
		if(loggedinU.getActive()==0) {
		return false;
		}
		else {
			return true;
		}
		
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
	
	public void approver(String s,int a) {
		for(Users m:users) {
			if(m.getUserName().equals(s)) {
				loggedinU=m;
				
				
			}}
		loggedinU.setActive(a);
		AccountDao acc = new AccountsDaoImpl();
		Account aplha= new Account();
		aplha.setAmmount(0);
		aplha.setActive(1);
		acc.createAccountPreparedStmt(aplha);
		UsersDao us = new UsersDaoImpl();
		us.updateUser(loggedinU);
		acc.createAccountToCus(acc.getLastMadeAccount(), acc.getcustId(loggedinU));
		
	}
	public boolean findType() {
		if(loggedinU.getAccounts().isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void approver2(String s,int a) {
		for(Users m:users) {
			if(m.getUserName().equals(s)) {
				loggedinU=m;
				
				
			}}
		loggedinU.setActive(a);
		
		
		UsersDao us = new UsersDaoImpl();
		us.updateUser(loggedinU);
		
		
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
		Users u =new Users( name, dOB, userName , password , 0);
        users.add(u);
        UsersDao us = new UsersDaoImpl();
        us.createUserPreparedStmt(u);
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
				AccountDao us = new AccountsDaoImpl();
				us.updateAccount(p);
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
		AccountDao us = new AccountsDaoImpl();
		accounts=us.retrieveAccountForAdmin();
		for (Account m:accounts) {
			if(m.getNumeber()==from) {
				return false;
			}
		}
		return true;
	}
	
	public boolean cheackRequest(int a) {
		for(Application i: applic) {
			if(i.getA()==a && i.getU().equals(loggedinU.getUserName())) {
				return true;
			}
			
		}
		return false;
		
	}
	
	public void setRequest(int a) {
		Application p=new Application();
		for(Account acc: accounts) {
			if (acc.getNumeber()==a) {
				p.setA(a);
			}
		}
			
		p.setU(loggedinU.getUserName());
		applic.add(p);
		AccountDao im=new AccountsDaoImpl();
		im.createAccountrec(p);
		
		
	}
	
	public void deposit(float amount, int from) {
		ArrayList<Account> temp = loggedinU.getAccounts();
		for (Account p:temp) {
			if(p.getNumeber()==from) {
				p.setAmmount(p.getAmmount()+amount);
				AccountDao us = new AccountsDaoImpl();
				us.updateAccount(p);
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
		AccountDao us = new AccountsDaoImpl();
		accounts=us.retrieveAccountForAdmin();
		for (Account b:accounts) {
			s+=b.getNumeber()+" "+b.getAmmount()+"\n";
		}
		return s;
		
	}
	public boolean hasaccreq(String a) {
		for (Application p:applic) {
			if (p.getU().equals(a)){
				return true;
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
						//=======================================================
					}
				}
			}
		}
	}

	public void addaccountto(String uname,int acc,int answer) {
		for(Application m: applic) {
			if(m.getU().equals(uname)&& m.getA()==acc) {
				m.setAnswer(answer);
				AccountDao us = new AccountsDaoImpl();
				us.updateAccountapp(m);
				if(answer==1) {
				for(Users p: users) {
					if(p.getUserName().equals(uname)) {
						for(Account a :accounts) {
							if (p.getRequest()==a.getNumeber()) {
								p.setAccounts(a);
								p.setRequest(0);
							}
						}
					}
						
						//========================================================
					}
				}
				}
				
			}
	}
	

	public StringBuffer getlogforuser(String uname) {
		UsersDao us = new UsersDaoImpl();
		
        return us.getUserlogg(uname);
        
		
	}
	public StringBuffer getlogforuseracc(String uname) {
		StringBuffer m= new StringBuffer();
		for(Users a: users) {
			ArrayList<Account> p = a.getAccounts();
			if (a.getUserName().equals(uname)) {
			for (Account aa: p) {
				
				UsersDao us = new UsersDaoImpl();
				
				m.append(us.getUserlogg2(aa.getNumeber()));
			}
			
			}
		}
		return m;
		
	}
	public StringBuffer getallapp() {
		StringBuffer app= new StringBuffer();
		for (Application i: applic) {
			app.append(i.getU()+" "+i.getA()+" "+i.getAnswer()+" \n");
		}
		return app;
		
	}
}
