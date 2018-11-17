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


/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private DatabaseController connector;
	String adminLoginMessage = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
        connector = new DatabaseController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		String username = request.getParameter("adminUsername");
		String password = request.getParameter("adminPassword");
		
		RequestDispatcher req = request.getRequestDispatcher("adminLogin");

		String sql = ("SELECT * FROM admin WHERE "
				+ "adminUsername = '" + username + "' AND "
				+ "adminPassword = '" + password + "'");
		
		try {
			conn = connector.getConnection();
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				req = request.getRequestDispatcher("admin");
				adminLoginMessage = "success";
				request.setAttribute("adminLoginMessage", adminLoginMessage);
				req.forward(request, response);
			}
			else {
				adminLoginMessage = "fail";
				request.setAttribute("adminLoginMessage", adminLoginMessage);
				req.forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
