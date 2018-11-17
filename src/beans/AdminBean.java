package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseAccess.DatabaseController;

public class AdminBean {
	private DatabaseController connector;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	
	public AdminBean() {
		connector = new DatabaseController();
	}
	
	public String checkAdmin(String username, String password) {
		try {
			conn = connector.getConnection();
			String query = ("SELECT * FROM admin WHERE "
					+ "adminUsername = '" + username + "' AND "
					+ "adminPassword = '" + password + "'");
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				return "success";
			}
			else {
				return "fail";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
