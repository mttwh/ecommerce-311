package model;

import java.util.Date;

public class CustomerOrder {
	
	private int orderId;
	private String customerUsername;
	private String customerEmail;
	private String orderTotal;
	private String orderDate;
	
	public CustomerOrder(String username, String email, String total, String date) {
		this.customerUsername = username;
		this.customerEmail = email;
		this.orderTotal = total;
		this.orderDate = date;
	}
	
	public CustomerOrder(int id,String username, String email, String total, String date) {
		this.orderId = id;
		this.customerUsername = username;
		this.customerEmail = email;
		this.orderTotal = total;
		this.orderDate = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
