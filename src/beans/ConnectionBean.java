package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import databaseAccess.DatabaseController;

public class ConnectionBean {
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	private DatabaseController connector;
	
	public ConnectionBean() {
		connector = new DatabaseController();
	}
	
	public void executeBeanUpdate(String query) throws SQLException {
		try {
			conn = connector.getConnection();
			statement = conn.prepareStatement(query);
			statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public List<List<String>> executeBeanQuery(String query) throws SQLException {
		List<List<String>> productValues = new ArrayList<>();
		List<String> tempValueList = null;
		
		try {
			conn = connector.getConnection();
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				tempValueList = new ArrayList<>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					try {
						int num = Integer.parseInt(rs.getString(rsmd.getColumnName(i)));
						tempValueList.add(Integer.toString(num));
					}
					catch(NumberFormatException e) {
						tempValueList.add(rs.getString(rsmd.getColumnName(i)));
					}
				}
				productValues.add(tempValueList);
			}
			return productValues;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return null;
	}
}
