package model;

public class Category {
	
	private int categoryId;
	private String categoryName;
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.categoryName = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
