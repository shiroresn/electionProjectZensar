package Setup;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.ConnectionProvider;

public class Installation {
	static Connection con;
	static Statement st;
	public static void main(String[] args) throws SQLException {

		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();
		
		String CreateVoterTable = "create table voter( id varchar(30) primary key, password varchar(30), fname varchar(15), mname varchar(15), name varchar(15), city varchar(15), dob date, address varchar(30) )";
		st.executeUpdate(CreateVoterTable);
		System.out.println("Voter Table Created");
		
		String CreateCandidateTable = "create table candidate( id varchar(30) primary key, cid varchar(30) primary key, password varchar(30), fname varchar(15), mname varchar(15), name varchar(15), city varchar(15), dob date, address varchar(30), party varchar(20) )";
		st.executeUpdate(CreateCandidateTable);
		System.out.println("Candidate Table Created");

	}
}
