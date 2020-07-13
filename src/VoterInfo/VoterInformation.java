package VoterInfo;

import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import Connection.ConnectionProvider;
import Date.MyDate;

public class VoterInformation {
	Connection con;
	Statement st;

private String id;
private String fname,mname,lname;
private String address;
public String city,date;
public String password;
private MyDate dob;

public String getInfo(String fname,String mname,String lname,String address,String city,MyDate dob,String password) throws SQLException {
	this.fname = fname;
	this.mname = mname;
	this.lname = lname;
	this.address = address;
	this.city = city;
	this.dob = dob;
	this.id = fname+lname+dob.yy;
	this.date = dob.getDate();
	this.password=password;
	
	int temp = saveDetails();
	if(temp==1)
	{
		return this.id;
	}
	return null;
}

public int saveDetails() throws SQLException {
	//creating connection
	con = ConnectionProvider.getConnectionWithDB();
	st= con.createStatement();
	
	//inserting information to database
	String sql1 = "insert into voter values('"+ id +"','"+ password +"','"+ fname +"','"+ mname +"','"+ lname +"','"+ city +"','"+ date +"','"+ address +"')";
	//String sql1 = "insert into voter values ('ShubhamShirore96','Shubham','Nandkumar','Shirore','Nashik','28-07-1996','Nashik')";
	try
	{
	st.executeUpdate(sql1);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		//if duplicate registration occurs then it will generate exception
		System.out.println("Already registered");
		return 0;
		
	}
	return 1;
	
}

public void getDetails(String id,String password) throws Exception
{
	con = ConnectionProvider.getConnectionWithDB();
	st= con.createStatement();
	String sql1 = "select * from voter where id = '"+id+"' and password = '"+password+"'";
	ResultSet rs = st.executeQuery(sql1);
	while(rs.next())
	{
		//Fetching Information from Database to local variables.
		this.fname = rs.getString("fname");
		this.mname = rs.getString("mname");
		this.lname = rs.getString("lname");
		this.address = rs.getString("address");
		this.city = rs.getString("city");
		this.date = rs.getString("dob");
		this.id = rs.getString("id");
		
	}
	if(this.id==null)
	{
		//if id not found in database then id = null;
		System.out.println("Voter not Registered");
	}
	else
	{
		//printing voter details
	System.out.println("ID : "+this.id);
	System.out.println("Name : "+this.fname+" "+this.mname+" "+this.lname);
	System.out.println("Address : "+this.address);
	System.out.println("City : "+this.city);
	System.out.println("DOB : "+this.date);
	}
}
}
