package Candidate;

import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import Connection.ConnectionProvider;
import Date.MyDate;

public class CandiInfo {
	Connection con;
	Statement st;

private String id;
private String fname,mname,lname;
private String address;
public String city,date;
public String password;
private MyDate dob;
public String nationality;
public String party;
public int deposit = 10000;
public String cid;




public String getInfo(String id,String fname,String mname,String lname,String address,String city,MyDate dob,String password,String nationality,String party) throws SQLException {
	this.fname = fname;
	this.mname = mname;
	this.lname = lname;
	this.address = address;
	this.city = city;
	this.dob = dob;
	this.id = id;
	this.date = dob.getDate();
	this.password=password;
	this.party = party;
	this.nationality = nationality;
	this.cid = ""+fname+party;
	int temp=0;
	if(nationality=="Indian")
	{
	temp = saveDetails();
	}
	if(temp==1)
	{
		return cid;
	}
	return null;
}

public int saveDetails() throws SQLException {
	con = ConnectionProvider.getConnectionWithDB();
	st= con.createStatement();
	
	/*String getid1 = "select * from voter where id = '"+id+"' and password = '"+password+"'";
	ResultSet rs = st.executeQuery(getid1);
	while(rs.next())
	{
		this.fname = rs.getString("fname");
	}
	System.out.println(fname);*/
	
	String fetchID = "select id from voter where id = '"+id+"'";
	ResultSet rs1 = st.executeQuery(fetchID);
	String tempID = null;
	while(rs1.next())
	{
		tempID = rs1.getString("id");
	}
	System.out.println("TempID :"+tempID);
	
	if(tempID!=null)
	{
	String sql1 = "insert into candidate values('"+ id +"','"+ cid +"','"+ password +"','"+ fname +"','"+ mname +"','"+ lname +"','"+ city +"','"+ date +"','"+ address +"','"+ party +"')";
	try
	{  
	st.executeUpdate(sql1);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Already registered");
		return 0;
		
	}
	return 1;
	}
	return 0;
}

public void getDetails(String id,String password) throws Exception
{
	con = ConnectionProvider.getConnectionWithDB();
	st= con.createStatement();
	String sql1 = "select * from voter where id = '"+id+"' and password = '"+password+"'";
	ResultSet rs = st.executeQuery(sql1);
	while(rs.next())
	{
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
		System.out.println("Voter not Registered");
	}
	else
	{
	System.out.println("ID : "+this.id);
	System.out.println("Name : "+this.fname+" "+this.mname+" "+this.lname);
	System.out.println("Address : "+this.address);
	System.out.println("City : "+this.city);
	System.out.println("DOB : "+this.date);
	}
}
}
