package NewCandidate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Connection.ConnectionProvider;
import Date.MyDate;


public class CandidateRegistration {

	static Connection con;
	static Statement st;

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		int ch = 1;
		//Welcome page
		System.out.println("Maharashtra State Election Board");
		System.out.println("Enter Your ID : ");
		String id = sc.next();
		System.out.println("Enter Your Password : ");
		String password = sc.next();

		//Connection
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();

		//candidate must have voter id
		String fetch = "select * from voter where id = '"+id+"' and password = '"+password+"'";
		ResultSet rs1 = st.executeQuery(fetch);

		String fname=null,mname=null,lname=null,city=null,dob=null,address=null,party=null,nationality=null;
		int deposit=0;
		String cid=null;

		while(rs1.next())
		{
			fname = rs1.getString("fname");
			mname = rs1.getString("mname");
			lname = rs1.getString("lname");
			city = rs1.getString("city");
			dob = rs1.getString("dob");
			address = rs1.getString("address");
		}

		if(fname!=null)
		{

			System.out.println("You are a voter");
			System.out.println("Enter Your Party Name : ");
			party = sc.next();

			//checking party has already fielded a candiate or not
			String getParty = "select party from candidate where party = '"+party+"'";
			ResultSet rs3 = st.executeQuery(getParty);
			String tempParty=null;
			while(rs3.next())
			{
				tempParty = rs3.getString(1);
			}
			if(tempParty==null)
			{
				System.out.println("Enter Your Nationality : ");
				nationality = sc.next();
				cid = ""+party+id;

				System.out.println("Please Confirm Your Date of Birth : ");
				System.out.println("DD : ");
				int dd = sc.nextInt();
				System.out.println("MM : ");
				int mm = sc.nextInt();
				System.out.println("YY : ");
				int yy = sc.nextInt();
				MyDate date = new MyDate(dd, mm, yy);
				//get date in string format
				String newDate = date.getDate();
				//				System.out.println(dob);
				//				System.out.println(dob.length());
				//
				//				char newDOB [] = dob.toCharArray();
				//				char newDOB1[]=null;
				//				for(int i=0;i<10;i++)
				//				{
				//					newDOB1[i] = newDOB[i];
				//				}
				//				//newDOB1[i]='\0';
				//				
				//				for(int i=0;i<10;i++)
				//				{
				//					System.out.println(newDOB1[i]);
				//				}
				//				String sqlD = "Select months_between (sysdate,'"+dob+"')/12  from dual";
				//
				//				ResultSet rs = st.executeQuery(sqlD);
				//				float age=0;
				//				while(rs.next())
				//				{
				//					age = Float.parseFloat(rs.getString(1));
				//				}
				//				
				//				System.out.println(dob);
				//				System.out.println(age);

				//checking Nationality = Indian and age should be 25+ 
				if(nationality.equals("Indian")&& date.TwentyFivePlus())
				{
					System.out.println("Congratulations... Registration Successful");
					System.out.println("Your Candidate ID : "+cid);

					String sql1 = "insert into candidate values('"+ id +"','"+ cid +"','"+ password +"','"+ fname +"','"+ mname +"','"+ lname +"','"+ city +"','"+ newDate +"','"+ address +"','"+ party +"')";
					st.executeUpdate(sql1);

				}
			}
			else
			{
				System.out.println("Party has already fielded a candiate");
			}
		}



	}
}
