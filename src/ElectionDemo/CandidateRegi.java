package ElectionDemo;

import java.sql.SQLException;
import java.util.Scanner;

import VoterInfo.VoterInformation;

import Candidate.CandiInfo;
import Date.MyDate;

public class CandidateRegi {
public static void main(String[] args) throws SQLException {
	Scanner sc = new Scanner(System.in);
	int ch = 1;
	do
	{
		//Welcome Page
	System.out.println("\n\n\nMaharashtra State Election Board");
	System.out.println("Candidate Registration Process");
	System.out.println("Enter Your VoterID : ");
	String id = sc.next();
	System.out.println("Please Enter Your Name : ");
	System.out.println("First Name : ");
	String fname = sc.next();
	System.out.println("Middle Name : ");
	String mname = sc.next();
	System.out.println("Last Name : ");
	String lname = sc.next();
	System.out.println("Enter Your Date of Birth : ");
	System.out.println("DD : ");
	int dd = sc.nextInt();
	System.out.println("MM : ");
	int mm = sc.nextInt();
	System.out.println("YY : ");
	int yy = sc.nextInt();
	MyDate dob = new MyDate(dd, mm, yy);
	System.out.println("Please Enter Your Address : ");
	String address = sc.next();
	System.out.println("Please Enter Your City : ");
	String city = sc.next();
	System.out.println("Enter Password : ");
	String password = sc.next();
	
	System.out.println("Enter Nationality : ");
	String nationality = sc.next();
	System.out.println("Enter Your Party Name : ");
	String party = sc.next();
	int deposit = 10000;
	System.out.println("Deposit : "+deposit);
	
	//Checking age of candidate
	if(dob.TwentyFivePlus())
	{
		
		CandiInfo c = new CandiInfo();
		String cid = c.getInfo(id,fname,mname,lname, address, city, dob,password,party,nationality);
		if(id.equals(null))
		{
			
		}
		else
		{
		System.out.println("You are Eligible to contest");
		System.out.println("Your ID : " +cid);
//		System.out.println("Your Password : " +id);
		System.out.println("Please Press 0 for Exit : ");
		}
		ch = sc.nextInt();
		
	}
	System.out.println("Please Press 0 for Exit : ");
	ch = sc.nextInt();
	}while(ch!=0);
	
	
	
}
}
