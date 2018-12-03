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
			String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9268278";
			String username = "sql9268278";
			String password = "LcXiCHr1wM";
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
