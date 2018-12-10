package beans;

import model.CartItem;
import model.CustomerOrder;
import model.CustomerOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBean {
	private ConnectionBean connectionBean = null;
	
	public OrderBean() {
		connectionBean = new ConnectionBean();
	}
	
	public void makeCustomerOrder(CustomerOrder order) {
		String username = order.getCustomerUsername();
		String email = order.getCustomerEmail();
		String total = order.getOrderTotal();
		String date = order.getOrderDate();
		
		String query = "INSERT INTO cust_order "
				+ "(customerUsername, customerEmail, orderTotal, orderDate) "
				+ "VALUES ('" + username + "', '"
				+ email + "', '"
				+ total + "', '"
				+ date + "');";	
		try {
			connectionBean.executeBeanUpdate(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public CustomerOrder getCustomerOrderById(int orderId) {
		String query = "SELECT * FROM cust_order "
				+ "WHERE orderId = '" + orderId + "';";
		CustomerOrder order = null;
		try {
			List<List<String>> customerOrderInfo = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < customerOrderInfo.size(); i++) {
				int id = Integer.parseInt(customerOrderInfo.get(i).get(0));
				String username = customerOrderInfo.get(i).get(1);
				String email = customerOrderInfo.get(i).get(2);
				String total = customerOrderInfo.get(i).get(3);
				String date = customerOrderInfo.get(i).get(4);
				order = new CustomerOrder(id, username, email, total, date);
			}
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CustomerOrderDetail> getOrderDetailsByOrderId(int orderId) {
		String query = "SELECT * FROM cust_order_detail "
				+ "WHERE orderId = '" + orderId + "';";
		List<CustomerOrderDetail> allDetailsOfOrder = new ArrayList<>();
		List<List<String>> customerOrderDetails = new ArrayList<>();
		try {
			customerOrderDetails = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < customerOrderDetails.size(); i++) {
				int id = Integer.parseInt(customerOrderDetails.get(i).get(0));
				String username = customerOrderDetails.get(i).get(1);
				String productName = customerOrderDetails.get(i).get(2);
				String productPrice = customerOrderDetails.get(i).get(3);
				int quantity = Integer.parseInt(customerOrderDetails.get(i).get(4));
				CustomerOrderDetail tempDetails = new CustomerOrderDetail(id, username, productName, productPrice, quantity);
				allDetailsOfOrder.add(tempDetails);
			}
			return allDetailsOfOrder;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveOrderDetails(List<CartItem> cartItems, CustomerOrder customerOrder) {
		int id = 0, quantity = 0;
		String username = null, productName = null, productPrice = null;
		
		for(int i = 0; i < cartItems.size(); i++) {
			id = customerOrder.getOrderId();
			username = customerOrder.getCustomerUsername();
			productName = cartItems.get(i).getProductName();
			productPrice = cartItems.get(i).getProductPrice();
			quantity = cartItems.get(i).getQuantity();
			String query = "INSERT INTO cust_order_detail "
					+ "(orderId, customerUsername, productName, productPrice, quantity) "
					+ "VALUES ('" + id + "', '"
					+ username + "', '"
					+ productName + "', '"
					+ productPrice + "', '"
					+ quantity + "');";	
			try {
				connectionBean.executeBeanUpdate(query);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public CustomerOrder getMostRecentOrderByUsername(String customerUsername) {
		List<List<String>> customerOrderInfo = new ArrayList<>();
		List<CustomerOrder> customersOrders = new ArrayList<>();
		String query = "SELECT * FROM cust_order "
				+ "WHERE customerUsername = '" + customerUsername + "';";
		try {
			customerOrderInfo = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < customerOrderInfo.size(); i++) {
				int id = Integer.parseInt(customerOrderInfo.get(i).get(0));
				String username = customerOrderInfo.get(i).get(1);
				String email = customerOrderInfo.get(i).get(2);
				String total = customerOrderInfo.get(i).get(3);
				String date = customerOrderInfo.get(i).get(4);
				CustomerOrder order = new CustomerOrder(id, username, email, total, date);
				customersOrders.add(order);
			}

			//get highest order id from list of customers orders.
			//order id's are numbered sequentially, so the highest number is the most recent order
			int maxValue = customersOrders.get(0).getOrderId();
			for(int i = 1; i < customersOrders.size(); i++) {
				if(customersOrders.get(i).getOrderId() > maxValue) {
					maxValue = customersOrders.get(i).getOrderId();
				}
			}
			CustomerOrder mostRecentOrder = getCustomerOrderById(maxValue);
			return mostRecentOrder;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
