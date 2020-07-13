package NewCandidate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.ConnectionProvider;

public class CandidateView {
	static Connection con;
	static Statement st;
	
	public static void main(String[] args) throws SQLException {
		//Connection
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		
		//Welcome page
		System.out.println("Enter Your Candidate ID : ");
		String cid = sc.next();
		System.out.println("Enter Your Password : ");
		String password = sc.next();
		
		//Checking id is present in database or not
		String fetch = "select * from candidate where cid = '"+cid+"' and password = '"+password+"'";
		ResultSet rs1 = st.executeQuery(fetch);
		
		String fname=null,mname=null,lname=null,city=null,dob=null,address=null,party=null,nationality=null;
		int deposit=0;
		String id=null;
		
		while(rs1.next())
		{
			fname = rs1.getString("fname");
			mname = rs1.getString("mname");
			lname = rs1.getString("name");
			city = rs1.getString("city");
			dob = rs1.getString("dob");
			address = rs1.getString("address");
			id = rs1.getString("id");
			party = rs1.getString("party");
			
		}
		
		if(fname==null)
		{
			System.out.println("Candidate not Found!");			
		}
		else
		{	
			//Displaying candidate information
			System.out.println("Candidate ID : "+cid);
			System.out.println("ID : "+id);
			System.out.println("Name : "+fname+" "+mname+" "+lname);
			System.out.println("Party Name : "+party);
			System.out.println("Address : "+address);
			System.out.println("City : "+city);
			System.out.println("DOB : "+dob);
		}
	}
}
