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

}
