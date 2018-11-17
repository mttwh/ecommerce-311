package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseAccess.DatabaseController;
import model.Customer;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private DatabaseController connector;

	
    public RegistrationController() {
        super();
        connector = new DatabaseController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement statement = null;
		
		//account info submitted from register.jsp's form to create Customer object
		try {
			conn = connector.getConnection();
			
			String username = request.getParameter("customerUsername");
			String password = request.getParameter("customerPassword");
			String fullName = request.getParameter("customerName");
			String email = request.getParameter("customerEmail");
			
			Customer customer = new Customer(username, password, fullName, email);
			
			if(username.isEmpty() || password.isEmpty())
			{
				RequestDispatcher req = request.getRequestDispatcher("RegistrationPage.jsp");
				req.include(request, response);
			}

			else
			{
				//new Customer info added to database so user's can login
				//need to add user session functionality to make user login mean anything
				 String sql = "INSERT INTO customer "
						+ "(customerName, customerEmail, customerUsername, "
						+ "customerPassword) "
						+ "VALUES ('"
						+ fullName + "', '" + email + "', '" + username + 
						"', '" + password + "');";
				
				statement = conn.prepareStatement(sql);
				statement.executeUpdate(); 
				
				RequestDispatcher req = request.getRequestDispatcher("index.jsp");
				req.forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	}

}
