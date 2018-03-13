package com.revature.dao;

import java.util.ArrayList;

import com.revature.bankProject.Application.Application;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;


public interface AccountDao {
	
	public void createAccount(Account account);
	
	public Account retrieveAccountById(int id);
	
	public ArrayList<Account> retrieveAccountForUser(String id);
	
	public ArrayList<Account> retrieveAccountForAdmin();
	
	public void updateAccount(Account account);
	
	public void deleteAccount(int id);
	
	public void createAccountPreparedStmt(Account account);

	public void createAccountrec(Application account);

	public ArrayList<Application> retrieveAccountForApp();

	public void updateAccountapp(Application account);

	public void deleteAccountapp(int id);

	public int getLastMadeAccount();
	public int getcustId(Users a);

	public void createAccountToCus(int a, int u);

}
