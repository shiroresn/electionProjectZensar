package Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import Connection.ConnectionProvider;

public class Authority {
	static Connection con;
	static Statement st;
	
	public static String userName = "Admin";
	public static String password = "Admin";
	public static void main(String[] args) throws SQLException {
		con = ConnectionProvider.getConnectionWithDB();
		st= con.createStatement();
		Random rand = new Random();

		//Admin Login
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Admin UserName : ");
		String userName1 = sc.next();
		System.out.println("Enter Admin Password : ");
		String password1 = sc.next();

		String countp=null,selectParties=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		int countParties=0;
		int i=1;
		if(userName1.equals(userName) && password1.equals(password))
		{
			//Checking number of parties for election
			countp = "select count(party) from candidate";
			rs1 = st.executeQuery(countp);

			while(rs1.next())
			{
				countParties = rs1.getInt(1);
			}

			System.out.println("Number of Parties : "+countParties);


			String[] p = new String[countParties+1];


			selectParties = "select party from candidate";
			rs2 = st.executeQuery(selectParties);

			while(rs2.next())
			{
				p[i] = rs2.getString(1);
				i++;

			}
			
			//Admin Menu
			System.out.println("Enter Choice : \n1.Start Election\n2.View VotedPeople List\n3.View Result\n4.Close Election\n Input : ");
			int choice;
			choice = sc.nextInt();

			switch (choice)
			{
			case 1:
				//Start Election
				//Creating All parties table for election
				String PartyTable=null;
				for(i=1;i<=countParties;i++)
				{
					System.out.println(p[i]+" Party Registered");
					PartyTable = "create table "+p[i]+" (vote int)";
					st.executeUpdate(PartyTable);
				}
				
				String NotaTable = "create table nota (id varchar(20) primary key)";
				st.executeUpdate(NotaTable);
				
				String AfterVoteTable = "create table AfterVote (id varchar(20) primary key)";
				st.executeUpdate(AfterVoteTable);
				System.out.println("AfterVote Table Created");
				break;

			case 2:
				//See Voted People list
				System.out.println("Voter List : ");

				String viewAfterVote = "select * from AfterVote";
				ResultSet rs5 = st.executeQuery(viewAfterVote);
				while(rs5.next())
				{
					String temp = rs5.getString("id");
					System.out.println(temp);

				}

				break;

			case 3:
				//View Result
				int[] x = new int[countParties+1];
				for(i=1;i<=countParties;i++)
				{
					String countVote = "Select count(vote) from "+p[i];
					ResultSet rs6 = st.executeQuery(countVote);
					while(rs6.next())
					{
						int tempCount = rs6.getInt(1);
						System.out.println(p[i] +" Votes : "+tempCount);
						x[i]=tempCount;
					}
				}
				
				String countNota = "Select count(id) from nota";
				ResultSet rs10 = st.executeQuery(countNota);
				int notaCount = 0;
				while(rs10.next())
				{
					notaCount = rs10.getInt(1);
				}
				System.out.println("Nota Votes : "+notaCount);

				//comparison
				x[0]=0;
				p[0]=" ";
				//Arrays.sort(x);
				
				
				
				for(i=1;i<countParties;i++)
				{
					if(x[i]>x[i+1])
					{
						int tempInt = x[i];
						
						String tempString = p[i];
						x[i]=x[i+1];
						p[i]=p[i+1];
						x[i+1]=tempInt;
						p[i+1]=tempString;
						
					}
				}
				
				//logic for if result is tie
				
				Integer[] tieParties = new Integer[countParties+1];	
				String[] tiePartiesString = new String[countParties+1];
				
				int k = 1;
				int winnerVotes = x[i];
				for(i=1;i<countParties+1;i++)
				{
					if(x[i]==winnerVotes)
						tieParties[k]=x[i];
						tiePartiesString[k]=p[i];
						k++;
				}
				
				
				int numberOfTie = tieParties.length;
				
				
				if(numberOfTie>1)
				{
				int tie = rand.nextInt()%(numberOfTie);
					if(tie<0)
					{
						tie=tie*-1;
					}
					if(tie==0)
					{
						tie++;
					}
					System.out.println("By Tie Breaker ");
					System.out.println(tie);
					System.out.println("Winner : "+tiePartiesString[tie]);
				}
				else
				{
				System.out.println("Winner : "+p[i]+" with "+x[i]+" votes");
				}
				
				//counting % of voting
				
				String TotalVoters = "select count(id) from voter";
				ResultSet rs7 = st.executeQuery(TotalVoters);
				int TotalVotersInteger=0;
				while(rs7.next())
				{
					TotalVotersInteger = rs7.getInt(1);
				}
				
				String VotedPeople = "select count(id) from AfterVote";
				ResultSet rs8 = st.executeQuery(VotedPeople);
				int TotalVotedPeople=0;
				while(rs8.next())
				{
					TotalVotedPeople = rs8.getInt(1);
				}

				int per=0;
				per = 100*TotalVotedPeople/TotalVotersInteger;
				System.out.println(per+"% Voting Done");
				System.out.println("Total Voters : "+TotalVotersInteger);
				System.out.println("Total People Voted : "+TotalVotedPeople);
				
				//Deposit lost Logic
				
				int OneSixth = TotalVotedPeople/6;
				System.out.println("Minimum Req Votes : "+OneSixth);
				
				for(i=1;i<countParties;i++)
				{
					if(x[i]<OneSixth)
					{
						System.out.println("Security Deposit Lost :"+p[i]);
					}
				}
				break;
				
			case 4:
				
				//Exit Election
				//Clearing all parties tables and clering all candidates for election
				
				String DeletePartyTable=null;
				for(i=1;i<=countParties;i++)
				{
					DeletePartyTable = "drop table "+p[i];
					st.executeUpdate(DeletePartyTable);
					System.out.println(p[i]+" Party Table Deleted");
				}
				String DeleteAfterVote = "drop table AfterVote";
				st.executeUpdate(DeleteAfterVote);
				System.out.println("AfterVote Table Dropped");
				
				String DeleteNota = "drop table nota";
				st.executeUpdate(DeleteNota);
				System.out.println("Nota Table Dropped");
				

				String ClearCandidates = "delete candidate";
				st.executeUpdate(ClearCandidates);
				System.out.println("All Candidates Cleared");
				
				break;
			}	
		}
		else
		{
			System.out.println("Login Failed");
		}
	}
}
