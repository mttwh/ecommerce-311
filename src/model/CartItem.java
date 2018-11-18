package model;

import java.text.DecimalFormat;

import model.Product;

public class CartItem {

	private Product product;
	private int quantity;
	private double itemTotal;
	
	public CartItem(Product product) {
		this.setProduct(product);
		setQuantity(1);
	}
	
	public CartItem() {
		
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
	
	//change to big decimal
	public double getItemTotal() {
		double itemTotal = quantity * Double.parseDouble(product.getProductPrice());
		DecimalFormat df = new DecimalFormat("#.##");
		itemTotal = Double.valueOf(df.format(itemTotal));
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}
	
}
