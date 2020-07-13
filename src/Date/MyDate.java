package Date;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.ConnectionProvider;

public class MyDate {
	int dd,mm;
	public int yy;
	
	//cretaing connection
	Connection con;
	Statement st;
	
	
	public MyDate(int dd,int mm,int yy)
	{
		this.dd=dd;
		this.mm=mm;
		this.yy=yy;
	}
	
	//return date in string format to save in database in perticular date format
	public String getDate() {
		String date = dd+"-"+mm+"-"+yy;
		return date;
	}
	
	//checks age is above 18
	public boolean EighteenPlus() throws SQLException
	{
		
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();
		
		String sqlD = "Select months_between (sysdate,'" + dd + "-" + mm +"-"+ yy +"')/12  from dual";

		ResultSet rs = st.executeQuery(sqlD);
		float age=0;
		while(rs.next())
		{
			age = Float.parseFloat(rs.getString(1));
		}
		/*System.out.println("Age : "+age);*/
		
		if(age>=18.00)
		return true;
		else
		{
			System.out.println("Sorry you can not vote");
		return false;
		}
	}
	

	//checks age is above 18
	public boolean TwentyFivePlus() throws SQLException
	{
		
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();
		
		String sqlD = "Select months_between (sysdate,'" + dd + "-" + mm +"-"+ yy +"')/12  from dual";

		ResultSet rs = st.executeQuery(sqlD);
		float age=0;
		while(rs.next())
		{
			age = Float.parseFloat(rs.getString(1));
		}
		/*System.out.println("Age : "+age);*/
		
		if(age>=25.00)
		return true;
		else
		{
			System.out.println("Sorry you are not eligible to contest");
		return false;
		}
	}
}
