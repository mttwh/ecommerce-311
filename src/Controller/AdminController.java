package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseAccess.DatabaseController;

import model.Product;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private DatabaseController connector;
       
    public AdminController() {
        super();
        connector = new DatabaseController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	//Allow admins to create new products and add them to the database
	//There is a drop down list so admin can select a relevant category for the new product
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = connector.getConnection();
			
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			String productPrice = request.getParameter("productPrice");
			String selectedCategory = request.getParameter("categorySelection");
			
			Product product = new Product(productName, productDescription, productPrice, selectedCategory);
			
			if(product.getProductName().isEmpty() || product.getCategoryName().isEmpty()) {
				RequestDispatcher req = request.getRequestDispatcher("admin");
				req.include(request, response);
			}
			
			else {
				//*** as of now it is possible to create duplicate product entries from admin page. MUST FIX!!!
				
				//Enter new product info into database to be displayed on the category page for relevant category
				String sql = "INSERT INTO product "
						+ "(productName, productDescription, productPrice, categoryName) "
						+ "VALUES ('" + productName + "', '"
						+ productDescription + "', '"
						+ productPrice + "', '"
						+ selectedCategory + "');";
				
				statement = conn.prepareStatement(sql);
				statement.executeUpdate();
				
				RequestDispatcher req = request.getRequestDispatcher("admin");
				req.forward(request, response);
				
			}
	} 
		catch(Exception e) {e.printStackTrace(); }
		finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

}}
