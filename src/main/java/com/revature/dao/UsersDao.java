package com.revature.dao;

import java.util.ArrayList;


import com.revature.bankProject.users.Users;


public interface UsersDao {
	
	public void createUser(Users flashCard);
	
	public Users retrieveUserById(String id);
	
	public ArrayList<Users> retrieveAllUser();
	
	public void updateUser(Users flashCard);
	
	public void deleteUser(String id);
	
	public void createUserPreparedStmt(Users flashCard);

	public StringBuffer getUserlogg(String uname);

	public StringBuffer getUserlogg2(int uname);

}
