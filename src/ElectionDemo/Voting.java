package ElectionDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.ConnectionProvider;

public class Voting {
	static Connection con;
	static Statement st;

	public static void main(String[] args) throws SQLException {
		//creating connection
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();

		int choice = 1;

		do
		{
			//welcome page
			Scanner sc = new Scanner(System.in);
			System.out.println("Maharashtra State Election Board");
			System.out.println("Enter Your ID : ");
			String id = sc.next();
			System.out.println("Enter Your Password : ");
			String password = sc.next();
			
			//checking voter is exist or not
			String fetch = "select * from voter where id = '"+id+"' and password = '"+password+"'";
			ResultSet rs1 = st.executeQuery(fetch);

			String fname=null,lname=null;
			while(rs1.next())
			{
				fname = rs1.getString("fname");
				lname = rs1.getString("lname");
			}

			//if voter is not exist then fname of voter is null
			if(fname==null)
			{
				System.out.println("Login Faild");
				System.exit(8);
			}

			System.out.println("Login Successful\nID : "+id+"\nWelcome "+fname+" "+lname);
			
			//displaying all parties and candidates information
			String displayCan = "Select fname,name,party from candidate";
			ResultSet rs3 = st.executeQuery(displayCan);

			System.out.println("Candidates are : \nName\t\tParty");
			while(rs3.next())
			{
				String c_fname = rs3.getString("fname");
				String c_lname = rs3.getString("name");
				String c_party = rs3.getString("party");
				System.out.println(c_fname+" "+c_lname+"\t"+c_party);
			}

			System.out.println("None of the above (NOTA)");
			System.out.println("Enter Your Choice : ");
			String ch = sc.next();
			
			try
			{
			String InsertChoice = "insert into "+ch+" values(1)";
			ResultSet rs4 = st.executeQuery(InsertChoice);
			}
			catch(Exception e)
			{
				System.out.println("Party does not exist.. \nPlease login again");
				System.exit(8);
			}
			
			//Saving vote to database
			try
			{
			String InsertVoter = "insert into aftervote values('"+id+"')";
			 st.executeUpdate(InsertVoter);
			}
			catch(Exception e)
			{
				System.out.println("You have already voted!!");
				System.exit(8);
			}
			System.out.println("Your Vote has been Successfully Casted!");
			
			System.out.println("Enter 0 for exit : ");
			choice = sc.nextInt();

		}while(choice!=0);

	}
}
