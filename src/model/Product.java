package model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private int productId;
	private String productName;
	private String productDescription;
	private String productPrice;
	private String categoryName;
	
	public Product(String name, String description, String price, String category) {
		this.productName = name;
		this.productDescription = description;
		this.productPrice = price;
		this.categoryName = category;
	}
	
	public Product() {
		
	}

	public String getProductName() {
		return productName;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
