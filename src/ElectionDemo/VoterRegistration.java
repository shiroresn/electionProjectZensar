package ElectionDemo;

import java.sql.SQLException;
import java.util.Scanner;

import VoterInfo.VoterInformation;

import Date.MyDate;

public class VoterRegistration {
public static void main(String[] args) throws SQLException {
	Scanner sc = new Scanner(System.in);
	int ch = 1;
	do
	{
		//welcome page
	System.out.println("\n\n\nMaharashtra State Election Board");
	System.out.println("Voter Registration Process");
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
	System.out.println("Enter New Password : ");
	String password = sc.next();
	
	if(dob.EighteenPlus())
	{
		
		VoterInformation v = new VoterInformation();
		//saving information to database
		String id = v.getInfo(fname,mname,lname, address, city, dob,password);

		if(id.equals(null))
		{
			System.out.println("");
		}
		else
		{
		System.out.println("You are Eligible for Voting");
		System.out.println("Your ID : " +id);
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
