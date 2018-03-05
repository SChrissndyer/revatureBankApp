package com.revature.bankProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.bankProject.users.Users;
import com.revature.bankProject.users.accounts.Account;

public class App 
{
	
	public static Bank b=new Bank();
    public static void main( String[] args ){
    	
    	//load data here
    	
    	
    	
       System.out.println("Hello and Welcome to the BANK!");
       boolean temp= false;
       while(temp==false) {
    	   getFindWitchUser();
    	   
       }
       
       
    }
    
    public static void logginUser() {
    	Scanner reader = new Scanner(System.in);
    	String userName="";
    	boolean temp =true;
    	while (temp==true) {
	    	System.out.println("Pleace enter in your User Name ");
	    	userName = reader.next();
	    	temp=b.loginUserName(userName);
	    	if(temp==true) {
	    		System.out.println("user not found try again ");
	    	}
        
    	}
    	temp=true;
    	while (temp==true) {
	    	System.out.println("Pleace enter in your password ");
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
        	b.setloggedinU();
        	System.out.println("user has not been approved yet");
        }
    	
    	
    }
    

	public static void getFindWitchUser() {
    	
    	Scanner reader = new Scanner(System.in);  
        System.out.println("Pleace select which user you are: ");
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
        	employee();
        	rightanswer=true;
        	break;
        case "4":
        	admin();
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
            System.out.println("Pleace select your choice: ");
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
    	            	b.setRequest(from);
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
		Scanner reader = new Scanner(System.in);  
        System.out.println("Pleace select your action: ");
        System.out.println("1: New customer request  ");
        System.out.println("2: returning customer info");
        System.out.println("3: request for linking accounts ");
        System.out.println("4: quit ");
        String answer = reader.next();
        boolean rightanswer= false;
        
        switch(answer) {
        case "1":
        	
        	rightanswer=true;
        	break;
        case "2":
        	
        	rightanswer=true;
        	break;
        case "3":
        	
        	rightanswer=true;
        	break;
        
        case "4":
        	
        	rightanswer=true;
        	break;
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;

        }
		
	}

	private static void employee() {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Pleace select your action: ");
        System.out.println("1: New customer request  ");
        System.out.println("2: returning customer info");
        System.out.println("3: request for linking accounts ");
        System.out.println("4: quit ");
        String answer = reader.next();
        boolean rightanswer= false;
        
        switch(answer) {
        case "1":
        	
        	rightanswer=true;
        	break;
        case "2":
        	
        	rightanswer=true;
        	break;
        case "3":
        	
        	rightanswer=true;
        	break;
        
        case "4":
        	
        	rightanswer=true;
        	break;
        	
        default :
        	System.out.println("wrong input plase try again");
        	break;

        }
		
		
	}

	private static void newCustomer() {
		Scanner reader = new Scanner(System.in);  
        System.out.println("Pleace enter in your name: ");
        String name = reader.next();
        System.out.println("Pleace enter in your Date of birht MM-DD-YYYY: ");
        String dOB = reader.next();
        String userName="";
        String password="";
        boolean tep=true;
        while(tep==true) {
        
	        System.out.println("Pleace enter in your user name: ");
	        userName = reader.next();
	        System.out.println("Pleace enter in your password: ");
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

	
}
