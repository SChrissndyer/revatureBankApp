package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.Application.Application;
import com.revature.bankProject.employee.Employee;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;
import com.revature.util.ConnectionFactory;

public class AccountsDaoImpl implements AccountDao {

	@Override
	public void createAccount(Account account) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO ACCOUNT (AMOUNT, IS_ACTIVE) VALUES('"+account.getAmmount()+"', '"+account.getActive()+"')";
			
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
	public Account retrieveAccountById(int id) {
		Account fc = new Account();
		
		String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				fc.setNumeber(rs.getInt("ACCOUNT_NUMBER"));
				fc.setAmmount(rs.getInt("AMOUNT"));
				fc.setActive(rs.getInt("IS_ACTIVE"));
				
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
	public ArrayList<Account> retrieveAccountForUser(String id) {
		ArrayList<Account> account = new ArrayList<Account>();
		
		String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER IN (SELECT ACCOUNT_NUMBER FROM ACCOUNT_TO_CUSTOMER WHERE ACCOUNT_TO_CUSTOMER.USER_NUMBER=(SELECT USER_NUMBER FROM CUSTOMER WHERE USER_NAME=?)) ";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Account fc = new Account();
				
				fc.setNumeber(rs.getInt(1));
				fc.setAmmount(rs.getInt(2));
				fc.setActive(rs.getInt(3));
				account.add(fc);
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
		
	}

	@Override
	public ArrayList<Account> retrieveAccountForAdmin() {
		ArrayList<Account> account = new ArrayList<Account>();
		
		String sql = "SELECT * FROM ACCOUNT";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Account fc = new Account();
				
				fc.setNumeber(rs.getInt(1));
				fc.setAmmount(rs.getInt(2));
				fc.setActive(rs.getInt(3));
				account.add(fc);
			}	
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public void updateAccount(Account account) {
		String sql = "UPDATE ACCOUNT SET AMOUNT=? WHERE ACCOUNT_NUMBER=?";
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setDouble(1, account.getAmmount());
			ps.setInt(2, account.getNumeber());
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
	public void deleteAccount(int id) {
		String sql = "UPDATE ACCOUNT SET IS_ACTIVE=0 WHERE ACCOUNT_NUMBER=?";
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			
			ps.setInt(2, id);
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

	public int getLastMadeAccount() {
		int a=0;
		String sql = "SELECT MAX(ACCOUNT_NUMBER) FROM ACCOUNT";
		
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				
				a=(rs.getInt("MAX(ACCOUNT_NUMBER)"));
				
				
			}	
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public void createAccountPreparedStmt(Account account) {
		String sql = "INSERT INTO ACCOUNT (AMOUNT,IS_ACTIVE) VALUES(? ,? )";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, account.getAmmount());
			
			ps.setInt(2, account.getActive());
			
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
	@Override
	public void createAccountToCus(int a,int u) {
		String sql = "INSERT INTO ACCOUNT_TO_CUSTOMER (USER_NUMBER,ACCOUNT_NUMBER) VALUES(? ,? )";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			conn.setAutoCommit(false);
			
			//Savepoint s = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, u);
			
			ps.setInt(2, a);
			
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

		@Override
		public void createAccountrec(Application account) {
			String sql = "INSERT INTO APPLICATIONS_TO_JOIN (U_ID,ACCOUNT_I,YES_NO) VALUES(?, ? ,? )";
			
			try {
				
				Connection conn = ConnectionFactory.getInstance().getConnection();
				
				conn.setAutoCommit(false);
				
				//Savepoint s = conn.setSavepoint("myFirstSavepoint");
				
				PreparedStatement ps = 
						conn.prepareStatement(sql);
				ps.setString(1, account.getU());
				ps.setInt(2, account.getA());
				
				
				ps.setInt(3, account.getAnswer());
				
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
		@Override
		public ArrayList<Application> retrieveAccountForApp() {
			ArrayList<Application> account = new ArrayList<Application>();
			
			String sql = "SELECT * FROM APPLICATIONS_TO_JOIN";
			
			try {
				Connection conn=ConnectionFactory.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					Application fc = new Application();
					
					fc.setU(rs.getString("U_ID"));
					fc.setA(rs.getInt("ACCOUNT_I"));
					fc.setAnswer(rs.getInt("YES_NO"));
					account.add(fc);
				}
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return account;
		}

		@Override
		public void updateAccountapp(Application account) {
			String sql = "UPDATE APPLICATIONS_TO_JOIN SET YES_NO=? WHERE ACCOUNT_I=? AND U_ID=?";
			try {
				
				Connection conn = ConnectionFactory.getInstance().getConnection();
				
				conn.setAutoCommit(false);
				
				//Savepoint s = conn.setSavepoint("myFirstSavepoint");
				
				PreparedStatement ps = 
						conn.prepareStatement(sql);
				
				ps.setInt(1, account.getAnswer());
				
				ps.setInt(2, account.getA());
				ps.setString(3, account.getU());
				
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
		public void deleteAccountapp(int id) {
			String sql = "{call CLEAN_APP}";
			try {
				
				Connection conn = ConnectionFactory.getInstance().getConnection();
				
				conn.setAutoCommit(false);
				
				//Savepoint s = conn.setSavepoint("myFirstSavepoint");
				
				CallableStatement ps = 
						conn.prepareCall(sql);
				
				
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
		public int getcustId(Users a) {
			int b=0;
			String sql = "SELECT * FROM CUSTOMER WHERE USER_NAME=?";
			
			try {
				Connection conn=ConnectionFactory.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, a.getUserName());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					b=rs.getInt("USER_NUMBER");
					
					
					
				}
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return b;
		}
		
		

}
