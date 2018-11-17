package model;

import model.Product;

public class CartItem {

	private Product product;
	private int quantity;
	private double itemTotal;
	
	public CartItem(Product product) {
		this.setProduct(product);
		setQuantity(1);
	}

	public int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}
	
	public String getProductName() {
		return product.getProductName();
	}

	private void setProduct(Product product) {
		this.product = product;
	}
	
	public void increaseQuantity() {
		quantity++;
	}
	
	public void decreaseQuantity() {
		quantity--;
	}
	
	public double getItemTotal() {
		double itemTotal = quantity * Double.parseDouble(product.getProductPrice());
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}
	
}
