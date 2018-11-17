package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseAccess.DatabaseController;

import beans.CategoryBean;
import beans.ProductBean;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Category;
import model.Product;

@WebServlet(name="/ControllerServlet",
		loadOnStartup = 1,
		//url patterns that are handled by this Servlet
		urlPatterns= {"/category",
					  "/register",
					  "/addToCart",
					  "/cart",
					  "/adminLogin",
					  "/admin"})
public class ControllerServlet extends HttpServlet {
	private DatabaseController connector;
	private List<Product> productList;
	private ProductBean productBean;

	
    public ControllerServlet() {
        super();
        connector = new DatabaseController();
        productBean = new ProductBean();
    }
    
    //init method called once upon application deployment.
    //category list will be stored in an application context variable for use across the application
    public void init() throws ServletException {
    	
    	String query = "SELECT * FROM category";
    	List categoryList = new ArrayList<Category>();
    	Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
    	
    	try {
    		conn = connector.getConnection();
    		statement = conn.prepareStatement(query);
    		rs = statement.executeQuery();

    		while(rs.next()) {
    			Category c = new Category();
    			c.setCategoryName(rs.getString("categoryName"));
    			categoryList.add(c);
    		}
    		
    		//category list is saved in the application context variable 'categories'. 
    		getServletContext().setAttribute("categories", categoryList);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(rs != null) {
    			try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		if(statement != null) {
    			try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		if(conn != null) {
    			try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category selectedCategory;
		CategoryBean categoryBean = new CategoryBean();
		ProductBean productBean = new ProductBean();
		HttpSession session = request.getSession();
		
		String userPath = request.getServletPath();

		if(userPath.equals("/category"))
		{
			String categoryName = request.getQueryString();
			
			if (categoryName != null) 
			{
				selectedCategory = categoryBean.getCategoryByName(categoryName);
				session.setAttribute("selectedCategory", selectedCategory);
				productList = productBean.getProductsByCategory(categoryName);
				
				session.setAttribute("productList", productList);
			}
		}
		
		else if (userPath.equals("/cart"))
		{
			
		}
		
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userPath = request.getServletPath();
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("shoppingCart");
		
		if(userPath.equals("/addToCart")) {
			
			if(cart == null)
			{
				cart = new Cart();
				session.setAttribute("shoppingCart", cart);
			}
			
			String productName = request.getParameter("productName");
			
			if(productName != null && !productName.isEmpty())
			{
				Product product = productBean.getProductByName(productName);
				cart.addItem(product);
				System.out.println(product.getProductName());
			}

			userPath = "/category";
		}
		
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
