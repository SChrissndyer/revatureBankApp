package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.admin.Admin;
import com.revature.bankProject.employee.Employee;
import com.revature.util.ConnectionFactory;

public class AdminDaoImpl implements AdminDao {

	@Override
	public void createAdmin(Admin admin) {
Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO ADMIN (ADMIN_NAME, ADMIN_NAME,ADMIN_PASSWORD) VALUES('"+admin.getName()+ "','"+admin.getUserName()+"', '"+admin.getPassword()+"')";
			
			int rowsAffected = statement.executeUpdate(sql);
			
			System.out.println("Rows updated " + rowsAffected);
			conn.close();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Admin retrieveAdminById(String id) {
		Admin fc = new Admin();
		
		String sql = "SELECT * FROM ADMIN WHERE ADMIN_NAME = ?";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				fc.setName(rs.getString(2));
				fc.setUserName(rs.getString(3));
				fc.setPassword(rs.getString(4));
				
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fc;
	}

	@Override
	public ArrayList<Admin> retrieveAllUser() {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		String sql = "SELECT * FROM ADMIN";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Admin fc = new Admin();
				
				fc.setName(rs.getString("ADMIN_NAME"));
				
				fc.setUserName(rs.getString("ADMIN_USER_NAME"));
				fc.setPassword(rs.getString("ADMIN_PASSWORD"));
				admins.add(fc);
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admins;
		
		
	}

	@Override
	public void updateAdmin(Admin admin) {
String sql = "UPDATE ADMIN SET ADMIN_NAME=?,ADMIN_USER_NAME=?,ADMIN_PASSWORD=? WHERE ADMIN_USER_NAME=?";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getUserName());
			ps.executeUpdate();
			
			//conn.rollback(s);
			
			// ADD LOG 
			
			conn.commit();
			
			conn.setAutoCommit(true);
			conn.close();
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void createAdminPreparedStmt(Admin admin) {
		String sql = "INSERT INTO ADMIN (ADMIN_NAME,ADMIN_USER_NAME,ADMIN_PASSWORD) VALUES(?, ? ,? )";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getUserName());
			
			ps.setString(5, admin.getPassword());
			
			ps.executeUpdate();
			
			//conn.rollback(s);
			
			conn.commit();
			
			conn.setAutoCommit(true);
			
			conn.close();
		
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
