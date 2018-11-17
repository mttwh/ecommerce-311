package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseAccess.DatabaseController;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Product;

public class ProductBean {
	
	private DatabaseController connector;
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	public ProductBean() {
		connector = new DatabaseController();
	}

	//returns a list of all products belonging to the specified category
	public List<Product> getProductsByCategory(String categoryName) {
		try {
			List<Product> productList = new ArrayList<>();
			conn = connector.getConnection();
			String query = "SELECT * FROM product WHERE categoryName = '" + categoryName + "';";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				String productName = rs.getString("productName");
				String productDescription = rs.getString("productDescription");
				String productPrice = rs.getString("productPrice");
				String productCategory = rs.getString("categoryName");
				
				Product product = new Product(productName, productDescription, productPrice, productCategory);
				productList.add(product);
			}
			return productList;
		}
		catch(Exception e) {e.printStackTrace(); }
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
	
	public void deleteProductByName(String productName) {
		try {
			conn = connector.getConnection();
			String query = "DELETE FROM product WHERE productName = '" + productName + "'";
			System.out.println(query);
			statement = conn.prepareStatement(query);	
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
		
	}
	
	public List<Product> getProducts() {
		try {
			List<Product> allProductList = new ArrayList<>();
			conn = connector.getConnection();
			String query = "SELECT * FROM product;";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next()) {
				String productName = rs.getString("productName");
				String productDescription = rs.getString("productDescription");
				String productPrice = rs.getString("productPrice");
				String productCategory = rs.getString("categoryName");
				
				Product product = new Product(productName, productDescription, productPrice, productCategory);
				allProductList.add(product);
			}
			return allProductList;
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
	
	public Product getProductByName(String productName) {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = connector.getConnection();
			String query = "SELECT * FROM product WHERE productName = '" + productName + "';";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				String prodName = rs.getString("productName");
				String prodDescription = rs.getString("productDescription");
				String prodPrice = rs.getString("productPrice");
				String prodCategory = rs.getString("categoryName");
				Product product = new Product(prodName, prodDescription, prodPrice, prodCategory);
				return product;
			}
			
			
		} catch (SQLException e) {
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
