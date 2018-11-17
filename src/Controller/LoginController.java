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
import javax.servlet.http.HttpSession;

import databaseAccess.DatabaseController;
import model.Customer;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private DatabaseController connector;
	String message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        connector = new DatabaseController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		RequestDispatcher req = request.getRequestDispatcher("index.jsp");
		session.invalidate();
		System.out.println("jjj");
		req.forward(request, response);
		//response.sendRedirect("index.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		String username = request.getParameter("customerUsername");
		String password = request.getParameter("customerPassword");
		
		RequestDispatcher req = request.getRequestDispatcher("index.jsp");
		String sql = ("SELECT * FROM customer WHERE "
				+ "customerUsername = '" + username + "' AND "
				+ "customerPassword = '" + password + "'");

		try {
			
			conn = connector.getConnection();
			
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			
			if(rs.next())
			{
				HttpSession session = request.getSession(true);
				Customer customer = new Customer();
				customer.setCustomerUsername(username);
				customer.setCustomerPassword(password);
				session.setAttribute("customer", customer);
				message = "success";
				request.setAttribute("message", message);
				req = request.getRequestDispatcher("index.jsp");
				req.forward(request, response);
			}
			else if(username == null || password == null) {
				message = "fail";
				request.setAttribute("message", message);
				req.forward(request, response);
			}
			else {
				message = "fail";
				request.setAttribute("message", message);
				req.forward(request, response);
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
