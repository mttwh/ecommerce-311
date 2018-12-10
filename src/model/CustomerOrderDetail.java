package model;

public class CustomerOrderDetail {
	
	private int orderId;
	private String customerUsername;
	private String productName;
	private String productPrice;
	private int quantity;
	
	public CustomerOrderDetail(int id, String username, String productName, String productPrice, int quantity) {
		this.orderId = id;
		this.customerUsername = username;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getOrderDetailSummary() {
		return "Product Name: " + getProductName() + " Product Price: " + getProductPrice() + " Quantity: " + getQuantity();
		
	}
}
