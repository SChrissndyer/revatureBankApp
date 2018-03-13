package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.admin.Admin;



public interface AdminDao {
	
	public void createAdmin(Admin admin);
	
	public Admin retrieveAdminById(String id);
	
	public ArrayList<Admin> retrieveAllUser();
	
	public void updateAdmin(Admin admin);
		
	public void createAdminPreparedStmt(Admin admin);

}
