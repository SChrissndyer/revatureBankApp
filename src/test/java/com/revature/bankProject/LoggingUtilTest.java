package com.revature.bankProject;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankProject.admin.Admin;
import com.revature.bankProject.employee.Employee;
import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;
import com.revature.eval.java.core.EvaluationService;

public class LoggingUtilTest {
	public static  Bank bank = new Bank();
	public static  Bank bank1 = new Bank();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<Account> accounts = new ArrayList<Account>();
		ArrayList<Users> users = new ArrayList<Users>();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		Users loggedinU;
		

		Users u =new Users( "Bill", "01-01-1990", "tester1" , "password" , true);
		Users u2 =new Users( "Bill", "01-01-1990", "tester" , "password" , false);
		Admin ad=new Admin("bob", "admin1","tester");
		Employee em=new Employee("bob", "employee1","tester");
		
		employees.add(em);
		admins.add(ad);
		Account a=new Account(5000);
		Account b=new Account(400);
		Account c=new Account(100);
		System.out.println(c.getNumeber());
		u.setAccounts(a);
		u.setAccounts(b);
		accounts.add(a);accounts.add(b);accounts.add(c);
        users.add(u); users.add(u2);
        bank.setLoggedinU(u);
        bank.setAccounts(accounts);
        bank.setAdmins(admins);
        bank.setEmployees(employees);
        bank.setUsers(users);
        bank1.setAccounts(accounts);
        bank1.setAdmins(admins);
        bank1.setEmployees(employees);
        bank1.setUsers(users);
        

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addingAccount() {
		ArrayList<Account> b = bank1.getAccounts();
		
		bank.addaccount();
		assertEquals(0, bank.getAccounts().size()- b.size());
	}
	@Test
	public void loginUserNamet() {
		assertFalse(bank.loginUserName("tester1"));
	}
	@Test
	public void loginUserNamef() {
		assertTrue(bank.loginUserName("to"));
	}
	
	@Test
	public void testsetloggedinU() {
		bank.setloggedinU("tester1");
		
	}
	
	@Test
	public void testgetamountf() {
		bank.getamount(0);
		
	}
	@Test
	public void testgetamountt() {
		bank.getamount(bank.getAccounts().get(0).getNumeber());
		
	}
	@Test
	public void testapprover() {
		
		bank.approver("tester",true);
		
	}
	
	@Test
	public void testloginUserpasswordf() {
		
		assertFalse(bank.loginUserpassword("tester","password"));
		
	}
	@Test
	public void testloginUserpasswordt() {
		
		assertTrue(bank.loginUserpassword("tester","passwor"));
		
	}
	
	@Test
	public void testloginEmployeeNamef() {
		
		assertFalse(bank.loginEmployeeName("employee1"));
		
	}
	@Test
	public void testloginEmployeeNamet() {
		
		assertTrue(bank.loginEmployeeName("employee"));
		
	}
	@Test
	public void testloginEmployeepasswordt() {
		
		assertTrue(bank.loginEmployeepassword("employee1","teste"));
		
	}
	@Test
	public void testloginEmployeepasswordf() {
		
		assertFalse(bank.loginEmployeepassword("employee1","tester"));
		
	}
	@Test
	public void testloginAdminNamef() {
		
		assertFalse(bank.loginAdminName("admin1"));
		
	}
	@Test
	public void testloginAdminNamet() {
		
		assertTrue(bank.loginAdminName("admin"));
		
	}
	
	@Test
	public void testloginAdminpasswordt() {
		
		assertTrue(bank.loginAdminpassword("admin1","teste"));
		
	}
	@Test
	public void testloginAdminpasswordf() {
		
		assertFalse(bank.loginAdminpassword("admin1","tester"));
		
	}
	@Test
	public void testadduser() {
	bank.adduser("jill", "09-22-1994", "user1", "password")	;
	
		
	}
	@Test
	public void testall() throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		//System.setIn(new FileInputStream("test.txt"));
		
		
		
	}
	

}
