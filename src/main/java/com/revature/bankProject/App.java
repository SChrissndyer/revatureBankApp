package com.revature.bankProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App implements Serializable 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3663561198035923325L;
	public static Bank b = new Bank();
    public static void main( String[] args ) throws ClassNotFoundException{
    	
    	//load data here
    	
    	
    	
       System.out.println("Hello and Welcome to the BANK!");
       boolean temp= false;
       b.loadAccountsAll();
       while(temp==false) {
    	   
    	   getFindWitchUser();
    	   
       }
       
       
    }
    
    public static void logginUser() {
    	Scanner reader = new Scanner(System.in);
    	String userName="";
    	boolean temp =true;
    	while (temp==true) {
	    	System.out.println("Please enter in your User Name ");
	    	userName = reader.next();
	    	temp=b.loginUserName(userName);
	    	if(temp==true) {
	    		System.out.println("user not found try again ");
	    	}
        
    	}
    	temp=true;
    	while (temp==true) {
	    	System.out.println("Please enter in your password ");
	    	String password = reader.next();
	    	temp=b.loginUserpassword(userName,password);
	    	
	    	if(temp==true) {
	    		System.out.println("invalade user name or password");
	    	}
    	}
        //once finished
        if (b.seenActive()) {
    	curentCustomer();
        }
        else {
        	
        	System.out.println("user has not been approved yet");
        }
    	
    	
    }
    public static void logginEmployee() {
    	Scanner reader = new Scanner(System.in);
    	String userName="";
    	boolean temp =true;
    	while (temp==true) {
	    	System.out.println("Please enter in your User Name ");
	    	userName = reader.next();
	    	temp=b.loginEmployeeName(userName);
	    	if(temp==true) {
	    		System.out.println("user not found try again ");
	    	}
        
    	}
    	temp=true;
    	while (temp==true) {
	    	System.out.println("Please enter in your password ");
	    	String password = reader.next();
	    	temp=b.loginEmployeepassword(userName,password);
	    	
	    	if(temp==true) {
	    		System.out.println("invalade user name or password");
	    	}
    	}
        //once finished
         
        	employee();
        
    	
    }
    public static void logginAdmin() {
    	Scanner reader = new Scanner(System.in);
    	String userName="";
    	boolean temp =true;
    	while (temp==true) {
	    	System.out.println("Please enter in your User Name ");
	    	userName = reader.next();
	    
	    	temp=b.loginAdminName(userName);
	    	if(temp==true) {
	    		System.out.println("user not found try again ");
	    	}
        
    	}
    	temp=true;
    	while (temp==true) {
	    	System.out.println("Please enter in your password ");
	    	String password = reader.next();
	    	temp=b.loginAdminpassword(userName,password);
	    	
	    	if(temp==true) {
	    		System.out.println("invalade user name or password");
	    	}
    	}
        //once finished
        
        admin();
        
    	
    	
    }
    

	public static void getFindWitchUser() {
    	
    	Scanner reader = new Scanner(System.in);  
        System.out.println("Please select which user you are: ");
        System.out.println("1: New customer ");
        System.out.println("2: returning customer ");
        System.out.println("3: employee ");
        System.out.println("4: admin ");
        System.out.println("5: quit ");
        String answer = reader.next();
        boolean rightanswer= false;
        
        switch(answer) {
        case "1":
        	newCustomer();
        	rightanswer=true;
        	break;
        case "2":
        	logginUser();
        	rightanswer=true;
        	break;
        case "3":
        	logginEmployee();
        	rightanswer=true;
        	break;
        case "4":
        	logginAdmin();
        	rightanswer=true;
        	break;
        case "5":
        	
        	System.exit(0);
        	rightanswer=true;
        	break;
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;

        }
    	
    	
    	
    }
    
    
    private static void curentCustomer() {
    	
    	System.out.println("Wellcome back "+b.getUser()+"!");
    	boolean temp1 = true;
    	while (temp1) {
    		Scanner reader = new Scanner(System.in);  
            System.out.println("Please select your choice: ");
            System.out.println("1: view accounts");
            System.out.println("2: Withdraw");
            System.out.println("3: deposit ");
            System.out.println("4: transfer ");
            System.out.println("5: request account conection ");
            System.out.println("6: quit ");
            
            String answer = reader.next();
            
            
            switch(answer) {
            case "1":
            	
            	System.out.println(b.getaccounts());
            
            	temp1=true;
            	break;
            case "2": 
            	try {
            	while(true) {
            		int pass=0;
            		
	            	System.out.println("PLease enter amount to withdraw: ");
	            	float amount = reader.nextFloat();
	            	if(amount<0) {
	            		pass=-1;
	            	}
	            	
	            	 
	            	
	            	System.out.println("PLease enter the account to withdraw from: ");
	            	int from = reader.nextInt();
	            	if(b.accountCheck( from)) {
	            		pass=-1;
	            	}
	            	if( pass==0){
	            		if( amount>b.getamount(from)) {
	            			pass=-1;
	            			System.out.println("insufishent funds!");
	            		}
	            		
	            		
	            	}
	            	if(pass==0) {
	            	b.withdraw(amount, from);
	            	break;
	            	}
            	}
            	} catch (InputMismatchException e) {
          		  
          		  System.out.println("Entered value is not an integer");
          		}
            	break;
            case "3":
            	try {
            	while(true) {
            		int pass=0;
	            	System.out.println("PLease enter amount to deposit: ");
	            	float amount = reader.nextFloat();
	            	if(amount<0) {
	            		pass=-1;
	            	}
	            	 
	            	
	            	System.out.println("PLease enter the account to deposit from: ");
	            	int from = reader.nextInt();
	            	if(b.accountCheck(from)) {
	            		pass=-1;
	            	}
	            	
	            	if(pass==0) {
	            	b.deposit(amount, from);
	            	break;
	            	}
            	}}catch (InputMismatchException e) {
            		  
            		  System.out.println("Entered value is not an integer");
            		}
            	
            	temp1=true;
            	
            	break;
            case "4":
            	try {
            	while(true) {
            		int pass=0;
	            	System.out.println("PLease enter amount to transfer: ");
	            	float amount = reader.nextFloat();
	            	if(amount<0) {
	            		pass=-1;
	            	}
	            	
	            	 
	            	
	            	System.out.println("PLease enter the account to withdraw from: ");
	            	int from = reader.nextInt();
	            	if(b.accountCheck( from)) {
	            		pass=-1;
	            	}
	            	if( pass==0){
	            		if( amount>b.getamount(from)) {
	            			pass=-1;
	            			System.out.println("insufishent funds!");
	            		}
	            	}
	            	System.out.println("PLease enter the account to deposit to: ");
		            int to = reader.nextInt();
		            if(b.accountCheck( to)) {
		            	pass=-1;
		            	
		            	}
	            		
	            		
	            	
	            	if(pass==0) {
	            	b.trasnfer(amount, from,to);
	            	break;
	            	}
            	}}
            	catch (InputMismatchException e) {
            
            		  System.out.println("Entered value is not an integer");
            		}
            	temp1=true;
            	break;
            	
            case "5":
            	try {
            		while(true) {
            			int pass=0;
            			System.out.println("PLease enter the account to conect to: ");
    	            	int from = reader.nextInt();
    	            	if(b.accountCheck( from)) {
    	            		if(b.accountChecks(from)) {
    	            			System.out.println("account not found");
    	            			pass=-1;
    	            		}
    	            		}else
    	            		{ 
    	            			pass=-1;	
    	            			System.out.println("this is already your account");
    	            		}
    	            	if (pass==0) {
    	            		
    	            	if(b.cheackRequest(from)) {
    	            		System.out.println("You are already requesting account");
    	            	}
    	            	else {
    	            	b.setRequest(from);
    	            	}
    	            	break;
    	            	}
            		}
            			
            	}
        	catch (InputMismatchException e) {
                
      		  System.out.println("Entered value is not an integer");
      		}
            	
            	break;
            	
            case "6":
            	
            	temp1=false;
            	System.out.println("logging out \n ...\n goodbye.");
            	break;
            	
            default :
            	System.out.println("wrong input plase try again");
            	break;
            }
    	}
		
	}

	
	private static void admin() {
		boolean rightanswer= false;
		while(rightanswer==false) {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Please select your action: ");
        System.out.println("1: Customer activate /deactivate");
        System.out.println("2: all cusotmer info and accounts  ");
        System.out.println("3: request for linking accounts ");
        System.out.println("4: add blank account ");
        System.out.println("5: Changes ");
        
        System.out.println("6: quit ");
        String answer = reader.next();
        
        
        switch(answer) {
        case "1":
        	while(true) {
            	System.out.println("please enter the userName to activate  ");
                String user = reader.next();
                
                if( b.loginUserName(user)) {
                	System.out.println("no user found try again");
                	
                }
                else {
                	int a= appden();
                	if (a==1) {
                		if(b.findType()) {
                			b.approver(user, a);
                			}
                			else {
                				b.approver2(user, a);
                			}
                    	}
                    	else {
                    		b.approver2(user, a);
                    	}
                	
                	break;
                }
                
            	}
                
            	
        	break;
        case "2":
        	b.loadAccountsAll();
        	System.out.println(b.getAllInfo());
        	break;
        case "3":
        	try {
        	while(true)
        	{
        		System.out.println(b.getallapp());
        		System.out.println("which user do you want to Look at for approval ");
                String uname = reader.next();
                if (b.loginUserName(uname)) {
                	System.out.println("not A user");
                }
                else {
                	if(b.hasaccreq(uname)) {
                		System.out.println("which account? ");
                        int acc = reader.nextInt();
                		int a= appden();
                		
                		b.addaccountto(uname,acc,a);
                		
                		
                		
                	}
                	else {
                		System.out.println("they did not request a join ");
                		break;
                	}
                }
        		
        		break;
        	}}catch (InputMismatchException e) {
                
      		  System.out.println("Entered value is not an integer");
      		}
        	
        	break;
        
        case "4":
        	b.addaccount();
        	break;

        case "5":
        	while(true)
        	{
        		System.out.println("which user do you want to Look at");
                String uname = reader.next();
                if (b.loginUserName(uname)) {
                	System.out.println("not A user");
                }
                else {
                	adminSubMenu(uname);
                	break;
                }
        	}
        	break;
        case "6":
	
        	rightanswer=true;
        	break;
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;

        }
		}
		
	}

	private static void adminSubMenu(String a) {
		b.setloggedinU(a);
		boolean rightanswer= false;
		while(rightanswer==false) {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Please select your action: ");
        System.out.println("1: Withdraw");
        System.out.println("2: deposit ");
        System.out.println("3: transfer ");
        System.out.println("4: loggs ");
        
        System.out.println("5: quit ");
        String answer = reader.next();
        switch(answer) {
        case "1": 
        	try {
        	while(true) {
        		int pass=0;
        		
            	System.out.println("PLease enter amount to withdraw: ");
            	float amount = reader.nextFloat();
            	if(amount<0) {
            		pass=-1;
            	}
            	
            	 
            	
            	System.out.println("PLease enter the account to withdraw from: ");
            	int from = reader.nextInt();
            	if(b.accountCheck( from)) {
            		pass=-1;
            	}
            	if( pass==0){
            		if( amount>b.getamount(from)) {
            			pass=-1;
            			System.out.println("insufishent funds!");
            		}
            		
            		
            	}
            	if(pass==0) {
            	b.withdraw(amount, from);
            	break;
            	}
        	}
        	} catch (InputMismatchException e) {
      		  
      		  System.out.println("Entered value is not an integer");
      		}
        	break;
        case "2":
        	try {
        	while(true) {
        		int pass=0;
            	System.out.println("PLease enter amount to deposit: ");
            	float amount = reader.nextFloat();
            	if(amount<0) {
            		pass=-1;
            	}
            	 
            	
            	System.out.println("PLease enter the account to deposit from: ");
            	int from = reader.nextInt();
            	if(b.accountCheck(from)) {
            		pass=-1;
            	}
            	
            	if(pass==0) {
            	b.deposit(amount, from);
            	break;
            	}
        	}}catch (InputMismatchException e) {
        		  
        		  System.out.println("Entered value is not an integer");
        		}
        	
        	
        	
        	break;
        case "3":
        	try {
        	while(true) {
        		int pass=0;
            	System.out.println("PLease enter amount to transfer: ");
            	float amount = reader.nextFloat();
            	if(amount<0) {
            		pass=-1;
            	}
            	
            	 
            	
            	System.out.println("PLease enter the account to withdraw from: ");
            	int from = reader.nextInt();
            	if(b.accountCheck( from)) {
            		pass=-1;
            	}
            	if( pass==0){
            		if( amount>b.getamount(from)) {
            			pass=-1;
            			System.out.println("insufishent funds!");
            		}
            	}
            	System.out.println("PLease enter the account to deposit to: ");
	            int to = reader.nextInt();
	            if(b.accountCheck( to)) {
	            	pass=-1;
	            	
	            	}
            		
            		
            	
            	if(pass==0) {
            	b.trasnfer(amount, from,to);
            	break;
            	}
        	}}
        	catch (InputMismatchException e) {
        
        		  System.out.println("Entered value is not an integer");
        		}
        	
        	break;
        	
        case "4":
        	while(true)
        	{
        		System.out.println("which user do you want to get loggs for ");
                String uname = reader.next();
                if (b.loginUserName(uname)) {
                	System.out.println("not A user");
                }
                else {
                	System.out.println(b.getlogforuser(uname));
                	System.out.println(b.getlogforuseracc(uname));
                	
                }
        		
        		break;
        	}
        	break;	
        	
        case "5":
        	rightanswer=true;
        	break;
        	
        default:
        	System.out.println("wrong input plase try again");
        	break;
        	
        }
		}
	}
	
	
	private static void employee() {
		boolean rightanswer= false;
		while(rightanswer==false) {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Please select your action: ");
        System.out.println("1: New customer request  ");
        System.out.println("2: returning customer info");
        System.out.println("3: quit ");
        String answer = reader.next();
        
        
        switch(answer) {
        case "1":
        	
        	while(true) {
        	System.out.println("please enter the userName to activate  ");
            String user = reader.next();
            
            if( b.loginUserName(user)) {
            	System.out.println("no user found try again");
            	
            }
            else {
            	int a= appden();
            	if (a==1) {
            		if(b.findType()) {
            			b.approver(user, a);
            			}
            			else {
            				b.approver2(user, a);
            			}
                	}
                	else {
                		b.approver2(user, a);
                	}
            	break;
            }
            
        	}
            
        	
        	break;
        case "2":
        	while(true) {
        	System.out.println("please enter the userName to view  ");
            String user = reader.next();
            
            if( b.loginUserName(user)) {
            	System.out.println("no user found try again");
            	
            }
            else {
            	
            	System.out.println(b.userInfo(user));
            	break;
            }
        	
        	}
        	break;
        
        
        case "3":
        	
        	rightanswer=true;
        	break;
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;

        }
		
		}
	}

	private static void newCustomer() {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Please enter in your name: ");
        String name = reader.next();
        System.out.println("Please enter in your Date of birht DD-MM-YYYY: ");
        String dOB = reader.next();
        String userName="";
        String password="";
        boolean tep=true;
        while(tep==true) {
        
	        System.out.println("Please enter in your user name: ");
	        userName = reader.next();
	        System.out.println("Please enter in your password: ");
	        password  = reader.next();
	        boolean inn=false;
	        inn=b.loginUserName(userName);
	        if(inn==true) {
	        	tep=false;
	        }
	        else {
	        	System.out.println("userName has been taken pick another one");
	        }
        }
        System.out.println(b.adduser(name, dOB, userName , password));
        
		
	}
	public static  int appden() {
		boolean rightanswer= false;
		int re = 0;
		while (rightanswer==false) {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Please select your action: ");
        System.out.println("1: approve  ");
        System.out.println("2: denie");
        
        String answer = reader.next();
     
        switch(answer) {
        case "1":
        	re=1;
        	rightanswer=true;
        	break;
        case "2":
        	re=0;
        	rightanswer=true;
        	break;
        
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;
		
        }
        
        }return re;
		}
	
	private static String getInput(String prompt) {
		Scanner reader = new Scanner(System.in); 
		String answer = reader.next();
		
		return answer;
		
	}
	
}
