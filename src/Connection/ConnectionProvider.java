package Connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider {
	static Connection con;
	static String userName,password;
	static public Connection getConnectionWithDB()
	{
		try
		{
		//Taking username and password from file
		FileReader fr = new FileReader("dbInfo.properties");
		Properties pr = new Properties();
		pr.load(fr);
		
		userName = pr.getProperty("un");
		password = pr.getProperty("password");
			
		//Creating Connection with SQL Database
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@hp:1521:XE",userName,password);
		System.out.println(con);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//return connection to other classes
		return con;
		}
	
	public static void main(String[] args) {
		new ConnectionProvider().getConnectionWithDB();
	}

}
