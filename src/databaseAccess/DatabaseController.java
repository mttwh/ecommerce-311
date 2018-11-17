package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseController 
{
	public DatabaseController()
	{
		
	}
	
	//allow database access to local database on my machine
	//will need to change when AWS database is set up
	public Connection getConnection()
	{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9265244";
			String username = "sql9265244";
			String password = "yhePLsL9yU";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
