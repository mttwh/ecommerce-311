package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseAccess.DatabaseController;

import model.Customer;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private DatabaseController connector;
       
    public LoginController() {
        super();
        connector = new DatabaseController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
    /*
	users will enter login info on the home page and if there is a username-password match for a 
	Customer in the database then the user will be successfully logged in.
	need to add user session functionality to make user login mean anything
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
			
		
		try {
			conn = connector.getConnection();
			
			
			String username = request.getParameter("customerUsername");
			String password = request.getParameter("customerPassword");
			
			if(username.isEmpty() || password.isEmpty())
			{
				RequestDispatcher req = request.getRequestDispatcher("index.jsp");
				req.include(request, response);
			}
			
			//will improve this functionality asap (save admin username and password in database and check it here)
			else if(username.equals("admin") && password.equals("admin")) {
				RequestDispatcher req = request.getRequestDispatcher("admin");
				req.forward(request, response);
			}
			
			else
			{
				String sql = ("SELECT * FROM customer WHERE "
						+ "customerUsername = '" + username + "' AND "
						+ "customerPassword = '" + password + "'");

				statement = conn.prepareStatement(sql);
				rs = statement.executeQuery();
				
				//Check if there is Customer result from the username-password combination entered
				if(rs.next())
				{
					//check if boolean value isAdmin is true (reminder to do this)
					
					//change to session.setAttribute to add user to the session
					request.setAttribute("customerUsername", username);
					RequestDispatcher req = request.getRequestDispatcher("index.jsp");
					req.forward(request, response);
				}
				else
				{
					System.out.println("Invalid Password");
					response.sendRedirect("index.jsp");
				}

			}
		}
		catch(Exception e) {
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

}
