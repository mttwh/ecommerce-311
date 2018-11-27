package beans;

import java.util.ArrayList;
import java.util.List;

import model.Category;


public class CategoryBean {
	
	private ConnectionBean connectionBean;

	
	public CategoryBean() {
		connectionBean = new ConnectionBean();
	}
	
	public List<Category> getCategories() {
		String query = "SELECT * FROM category";
		List<List<String>> categoryQueryList = null;
		List<Category> categoryList = new ArrayList<>();
		String categoryName = null;
		
		try {
			categoryQueryList = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < categoryQueryList.size(); i++) {
				categoryName = categoryQueryList.get(i).get(0);
				Category category = new Category(categoryName);
				categoryList.add(category);
			}
			return categoryList;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Category getCategoryByName(String name) {
		String query = "SELECT * FROM category WHERE categoryName = '" + name + "';";
		List<List<String>> categoryQueryList = null;
		String categoryName = null;
		Category category = null;
		
		try {
			categoryQueryList = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < categoryQueryList.size(); i++) {
				categoryName = categoryQueryList.get(i).get(0);
				category = new Category(categoryName);
				return category;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
