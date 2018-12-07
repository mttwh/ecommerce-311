/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.WishList;
import beans.ProductBean;
import model.Customer;

/**
 *
 * @author hrai36
 */
public class WishlistBean {private ConnectionBean connectionBean;

        ProductBean productBean;
        CustomerBean customerBean;
	
	public WishlistBean() {
		connectionBean = new ConnectionBean();
                productBean = new ProductBean();
                customerBean = new CustomerBean();
	}
	
	public List<Product> getWishlistItems(String newCustomerName) {
               // String query = "SELECT * FROM wishlist WHERE username = '" + newCustomerName + "';";
	     String query = "SELECT product FROM wishlist WHERE username = 'jordanrai9';";
		
               List<List<String>> wishlistQueryList = null;
		List<Product> products = new ArrayList<>();
		String productName = null;
		
		try {
			wishlistQueryList = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < wishlistQueryList.size(); i++) {
                                
				productName = wishlistQueryList.get(i).get(0);
                                
                                Product product = productBean.getProductByName(productName);
				products.add(product);
			}
                        
                        return products;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
        
        public void addToWishlist(WishList wishlist) {
		String newProductName = wishlist.product.getProductName();
		String newCustomerName = wishlist.customer.getCustomerUsername();
		String query = "INSERT INTO wishlist "
				+ "(product, username) "
				+ "VALUES ('" + newProductName + "', '"
				+ newCustomerName + "');";	
		try {

			connectionBean.executeBeanUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*public Category getCategoryByName(String name) {
		String query = "SELECT * FROM category WHERE categoryName = '" + name + "';";
		List<List<String>> categoryQueryList = null;
		String categoryName = null;
		Category category = null;
		
		try {
			categoryQueryList = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < categoryQueryList.size(); i++) {
				categoryName = categoryQueryList.get(i).get(0);
				category = new Category(categoryName);
				return category;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}


