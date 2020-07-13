package ElectionDemo;

import java.util.Scanner;

import VoterInfo.VoterInformation;

public class ViewVoterInfo {
public static void main(String[] args) throws Exception {
	Scanner sc = new Scanner(System.in);
	int ch = 1;
	do
	{
		//welcome page
	System.out.println("Maharashtra State Election Board");
	System.out.println("Enter Your ID : ");
	String id = sc.next();
	System.out.println("Enter Your Password : ");
	String password = sc.next();
	
	VoterInformation v = new VoterInformation();
	//calling view Details method
	v.getDetails(id, password);
	
	System.out.println("Enter 0 for End : ");
	ch = sc.nextInt();
	}while(ch!=0);
}
}
