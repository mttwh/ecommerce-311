package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import databaseAccess.DatabaseController;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Product;

public class ProductBean {
	
	private ConnectionBean connectionBean;
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	public ProductBean() {
		connectionBean = new ConnectionBean();
	}
	
	public void replaceProduct(Product product, String oldProductName) {
		String newProductName = product.getProductName();
		String newProductDescription = product.getProductDescription();
		String newProductPrice = product.getProductPrice();
		String newSelectedCategory = product.getCategoryName();
		Product newProduct = new Product(newProductName, newProductDescription, newProductPrice, newSelectedCategory);
		
		try {
			deleteProductByName(oldProductName);
			addProduct(newProduct);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addProduct(Product product) {
		String newProductName = product.getProductName();
		String newProductDescription = product.getProductDescription();
		String newProductPrice = product.getProductPrice();
		String newSelectedCategory = product.getCategoryName();
		String query = "INSERT INTO product "
				+ "(productName, productDescription, productPrice, categoryName) "
				+ "VALUES ('" + newProductName + "', '"
				+ newProductDescription + "', '"
				+ newProductPrice + "', '"
				+ newSelectedCategory + "');";	
		try {

			connectionBean.executeBeanUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Product> getProductsByCategory(String categoryName) {
		try {
			List<Product> productList = new ArrayList<>();
			String name, description, price, category = null;
			String query = "SELECT * FROM product WHERE categoryName = '" + categoryName + "';";
			List<List<String>> productValues = connectionBean.executeBeanQuery(query);
			
			for(int i = 0; i < productValues.size(); i++) {
				name = productValues.get(i).get(1);
				description = productValues.get(i).get(2);
				price = productValues.get(i).get(3);
				category = productValues.get(i).get(4);

				Product tempProduct = new Product(name, description, price, category);
				productList.add(tempProduct);
			}
			
			return productList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteProductByName(String productName) {
		try {
			String query = "DELETE FROM product WHERE productName = '" + productName + "'";
			connectionBean.executeBeanUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Product> getProducts() {
		try {
			List<Product> allProductList = new ArrayList<>();
			String query = "SELECT * FROM product;";
			String name, description, price, category = null;
			List<List<String>> productValues = connectionBean.executeBeanQuery(query);

			for(int i = 0; i < productValues.size(); i++) {
				name = productValues.get(i).get(1);
				description = productValues.get(i).get(2);
				price = productValues.get(i).get(3);
				category = productValues.get(i).get(4);

				Product tempProduct = new Product(name, description, price, category);
				allProductList.add(tempProduct);
			}
			
			return allProductList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product getProductByName(String productName) {
		String query = "SELECT * FROM product WHERE productName = '" + productName + "';";
		String name, description, price, category = null;
		List<List<String>> gottenProduct = null;
		Product tempProduct = null;
		
		try {
			gottenProduct = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < gottenProduct.size(); i++) {
				name = gottenProduct.get(i).get(1);
				description = gottenProduct.get(i).get(2);
				price = gottenProduct.get(i).get(3);
				category = gottenProduct.get(i).get(4);

				tempProduct = new Product(name, description, price, category);
			}
			return tempProduct;
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
