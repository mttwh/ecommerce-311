package model;

import java.util.Date;

public class CustomerOrder {
	
	private int orderId;
	private Date dateCreated;
	private double orderAmount;
	private int customerId;
	
	public CustomerOrder() {
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}


	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
