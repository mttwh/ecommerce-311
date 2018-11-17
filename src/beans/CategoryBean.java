package beans;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseAccess.DatabaseController;


import model.Category;

public class CategoryBean {
	
	private DatabaseController connector;
	
	public CategoryBean() {
		connector = new DatabaseController();
	}
	
	//returns Category object based on category name
	public Category getCategoryByName(String name) {
		Category c;
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = connector.getConnection();
			String query = "SELECT * FROM category WHERE categoryName = '" + name + "';";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery(query);
			
			if(rs.next()) {
				c = new Category(rs.getString("categoryName"));
				return c;
			}
			
		} 
		catch(Exception e) {e.printStackTrace();}
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
