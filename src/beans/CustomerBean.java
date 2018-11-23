package beans;

import java.util.List;
import java.util.ArrayList;
import model.Customer;


public class CustomerBean {

	private ConnectionBean connectionBean = null;
	
	public CustomerBean() {
		connectionBean = new ConnectionBean();
	}
	
	
	public void registerCustomer(Customer customer) {
		String fullName = customer.getCustomerName();
		String email = customer.getCustomerEmail();
		String username = customer.getCustomerUsername();
		String password = customer.getCustomerPassword();
		
		String query = "INSERT INTO customer "
				+ "(customerName, customerEmail, customerUsername, "
				+ "customerPassword) "
				+ "VALUES ('"
				+ fullName + "', '" + email + "', '" + username + 
				"', '" + password + "');";
		try {
			connectionBean.executeBeanUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Customer getCustomerByUsername(String username) {
		String query = ("SELECT * FROM customer WHERE " 
				+ "customerUsername = '" + username + "'");
		List<List<String>> customerInfo = new ArrayList<>();
		String fullName, email, uname, password = null;
		Customer customer = null;
		
		try {
			customerInfo = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < customerInfo.size(); i++) {
				fullName = customerInfo.get(i).get(1);
				email = customerInfo.get(i).get(2);
				uname = customerInfo.get(i).get(3);
				password = customerInfo.get(i).get(4);
				customer = new Customer(uname, password, fullName, email);
			}
			return customer;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean validateCredentials(String username, String password) {
		String query = ("SELECT * FROM customer WHERE "
				+ "customerUsername = '" + username + "' AND "
				+ "customerPassword = '" + password + "'");
		List<List<String>> customerCreds = null;
		String uname = null, psswd = null;
		
		try {
			customerCreds = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < customerCreds.size(); i++) {
				uname = customerCreds.get(i).get(3);
				psswd = customerCreds.get(i).get(4);
			}
			
			if(uname != null && psswd != null) {
				return true;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}
