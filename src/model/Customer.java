package model;

public class Customer {
	
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerUsername;
	private String customerPassword;
	private String customerPhone;
	private String customerAddress;
	
	public Customer(String username, String password, String fullName, String email) {
		this.customerUsername = username;
		this.customerPassword = password;
		this.customerName = fullName;
		this.customerEmail = email;
	}
	
	public Customer() {
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

}
