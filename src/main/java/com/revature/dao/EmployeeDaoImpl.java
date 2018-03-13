package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.employee.Employee;

import com.revature.util.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void createEmployee(Employee flashCard) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_NAME, EMPLOYEE_NAME,EMPLOYEE_PASSWORD) VALUES('"+flashCard.getName()+ "','"+flashCard.getUserName()+"', '"+flashCard.getPassword()+"')";
			
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
	public Employee retrieveEmployeeById(String id) {
		Employee fc = new Employee();
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_NAME = ?";
		
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
	public ArrayList<Employee> retrieveAllEmployee() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		String sql = "SELECT * FROM EMPLOYEE";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Employee fc = new Employee();
				
				fc.setName(rs.getString("EMPLOYEE_NAME"));
				
				fc.setUserName(rs.getString("EMPLOYEE_USER_NAME"));
				fc.setPassword(rs.getString("EMPLOYEE_PASWORD"));
				employees.add(fc);
			}
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
		
	}

	@Override
	public void updateEmployee(Employee flashCard) {
		String sql = "UPDATE EMPLOYEE SET EMPLOYEE_NAME=?,EMPOLYEE_USER_NAME=?,EMPLOYEE_PASSWORD=? WHERE EMPOLYEE_USER_NAME=?";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, flashCard.getName());
			ps.setString(2, flashCard.getUserName());
			ps.setString(3, flashCard.getPassword());
			ps.setString(4, flashCard.getUserName());
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
	public void createEmployeePreparedStmt(Employee flashCard) {
		String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_NAME,EMPLOYEE_USER_NAME,EMPLOYEE_PASSWORD) VALUES(?, ? ,? )";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, flashCard.getName());
			ps.setString(2, flashCard.getUserName());
			
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

}
