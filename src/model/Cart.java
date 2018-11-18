package model;

import java.util.ArrayList;
import java.util.List;

import model.Product;

public class Cart {

	private List<CartItem> cartItems;
	private int totalItemQuantity; //number of items in cart
	private double cartTotal;
	
	public Cart() {
		//initialize cart variables
		setCartItems(new ArrayList<>());
		setCartTotal(0.00); 
	}
	
	public void addItem(Product product) {
		boolean itemPresent = false;
		
		for(int i = 0; i < getCartItems().size(); i++)
		{
			if(getCartItems().get(i).getProductName().equals(product.getProductName())) {
				
				itemPresent = true;
				getCartItems().get(i).increaseQuantity();
			}
		}
		
		if(!itemPresent) 
		{
			CartItem newItem = new CartItem(product);
			getCartItems().add(newItem);
		}
	}
	
	public void removeItemByName(String itemName) {
		for(int i = 0; i < getCartItems().size(); i++) {
			if(getCartItems().get(i).getProductName().equals(itemName)) {
				getCartItems().remove(i);
			}
		}
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public CartItem getCartItemByName(String itemName) {
		CartItem item = new CartItem();
		for(int i = 0; i < getCartItems().size(); i++) {
			if(getCartItems().get(i).getProductName().equals(itemName)) {
				item = getCartItems().get(i);
			}
		}
		return item;
	}
	
	public double calculateTotal() {
		
		for(int i = 0; i < getCartItems().size(); i++) {
			Product product = getCartItems().get(i).getProduct();
			int quantity = getCartItems().get(i).getQuantity();;
			setCartTotal(getCartTotal() + quantity * Double.parseDouble(product.getProductName()));
		}
		return getCartTotal();
	}

	double getCartTotal() {
		return cartTotal;
	}

	void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

	void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	int getTotalItemQuantity() {
		return totalItemQuantity;
	}

	void setTotalItemQuantity(int totalItemQuantity) {
		this.totalItemQuantity = totalItemQuantity;
	}
	
}
