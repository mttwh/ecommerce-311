package beans;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseAccess.DatabaseController;


import model.Category;

public class CategoryBean {
	
	private DatabaseController connector;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	private List<Category> categoryList = new ArrayList<>();

	
	public CategoryBean() {
		connector = new DatabaseController();
	}
	
	public List<Category> getCategories() {
		
		try {
			conn = connector.getConnection();
			String query = "SELECT * FROM category";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				Category c = new Category();
				c.setCategoryName(rs.getString("categoryName"));
				categoryList.add(c);
			}
			
			return categoryList;
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
	
	//returns Category object based on category name
	public Category getCategoryByName(String name) {
		Category c;
		
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
