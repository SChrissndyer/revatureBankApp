package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.users.Users;

import com.revature.util.ConnectionFactory;

public class UsersDaoImpl implements UsersDao {

	public void createUser(Users flashCard) {
		
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO CUSTOMER (NAME, USER_NAME,USER_PASSWORD,DOB,ACTIVE) VALUES('"+flashCard.getName()+ "','"+flashCard.getUserName()+"', '"+flashCard.getPassword()+"', '"+flashCard.getBirthDay()+"', '"+flashCard.getActive()+"')";
			
			int rowsAffected = statement.executeUpdate(sql);
			
			System.out.println("Rows updated " + rowsAffected);
			conn.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Users retrieveUserById(String id) {
		Users fc = new Users();
		
		String sql = "SELECT * FROM CUSTOMER WHERE USER_NAME = ?";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				
				fc.setName(rs.getString(2));
				fc.setActive(rs.getInt(3));
				fc.setBirthDay(rs.getString(4));
				fc.setUserName(rs.getString(5));
				fc.setPassword(rs.getString(6));
				
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

	public ArrayList<Users> retrieveAllUser() {
		
		ArrayList<Users> users = new ArrayList<Users>();
		
		String sql = "SELECT * FROM CUSTOMER";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()){
				Users fc = new Users();
				
				fc.setName(rs.getString("NAME"));
				fc.setActive(rs.getInt("ACTIVE"));
				fc.setBirthDay(rs.getString("DOB").substring(0,10));
				fc.setUserName(rs.getString("USER_NAME"));
				fc.setPassword(rs.getString("USER_PASSWORD"));
				users.add(fc);
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}

	public void updateUser(Users flashCard) {
		String sql = "UPDATE CUSTOMER SET NAME=?, DOB=(TO_DATE(?,'YYYY-MM-DD')),USER_NAME=?,USER_PASSWORD=?,ACTIVE=? WHERE USER_NAME=?";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, flashCard.getName());
			ps.setString(2, flashCard.getBirthDay().substring(0, 10));
			ps.setString(3, flashCard.getUserName());
			ps.setString(4, flashCard.getPassword());
			ps.setInt(5, flashCard.getActive());
			ps.setString(6, flashCard.getUserName());
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

	public void deleteUser(String id) {
		String sql = "UPDATE CUSTOMER SET ACTIVE = 0 WHERE USER_ID = ?";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, id);
			
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

	public void createUserPreparedStmt(Users flashCard) {
		
		String sql = "INSERT INTO CUSTOMER (NAME, USER_NAME,ACTIVE,DOB,USER_PASSWORD) VALUES(?, ? ,? ,(TO_DATE(?,'YYYY-MM-DD')), ?)";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, flashCard.getName());
			ps.setString(2, flashCard.getUserName());
			ps.setInt(3, flashCard.getActive());
			ps.setString(4, flashCard.getBirthDay());
			ps.setString(5, flashCard.getPassword());
			
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
	public StringBuffer getUserlogg(String a) {
		
		String sql = "SELECT * FROM LOG_USERDATA WHERE WORD_NUM=(SELECT USER_NUMBER FROM CUSTOMER WHERE USER_NAME=?) ORDER BY Change_date";
		StringBuffer aa= new StringBuffer();
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, a);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				
				aa.append(rs.getString(1)+" ");
				aa.append(rs.getInt(2)+" ");
				aa.append(rs.getString(3)+" ");
				aa.append(rs.getString(4)+" ");
				aa.append(rs.getString(5)+"\n");
				
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aa;
	}
public StringBuffer getUserlogg2(int a) {
		
		String sql = "SELECT * FROM LOG_USERDATA WHERE WORD_NUM=? ORDER BY Change_date";
		StringBuffer aa= new StringBuffer();
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setInt(1, a);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				
				aa.append(rs.getString(1)+" ");
				aa.append(rs.getInt(2)+" ");
				aa.append(rs.getString(3)+" ");
				aa.append(rs.getString(4)+" ");
				aa.append(rs.getString(5)+"\n");
				
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aa;
	}




}
