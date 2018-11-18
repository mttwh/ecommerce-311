package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseAccess.DatabaseController;
import beans.AdminBean;
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
					  "/admin",
					  "/deleteProduct",
					  "/addProduct",
					  "/logout",
					  "/updateQuantity"})
public class ControllerServlet extends HttpServlet {
	private DatabaseController connector;
	private List<Product> categoryProductList;
	private List<Product> allProductList;
	private ProductBean productBean;
	private AdminBean adminBean;
	private CategoryBean categoryBean;
	private String adminLoginMessage = null;

	
    public ControllerServlet() {
        super();
        connector = new DatabaseController();
        productBean = new ProductBean();
        adminBean = new AdminBean();
        categoryBean = new CategoryBean();
       
    }
    
    //init method called once upon application deployment.
    //category list will be stored in an application context variable for use across the application
    public void init() throws ServletException {
    	
    	List categoryList = new ArrayList<Category>();
    	categoryList = categoryBean.getCategories();
    	getServletContext().setAttribute("categories", categoryList);
    	
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category selectedCategory;
		CategoryBean categoryBean = new CategoryBean();
		ProductBean productBean = new ProductBean();
		HttpSession session = request.getSession();
		
		String userPath = request.getServletPath();

		if(userPath.equals("/admin")) {	
			allProductList = productBean.getProducts();
			session.setAttribute("allProductList", allProductList);
			return;
		}
		
		else if(userPath.equals("/category"))
		{
			String categoryName = request.getQueryString();
			
			if (categoryName != null) 
			{
				selectedCategory = categoryBean.getCategoryByName(categoryName);
				session.setAttribute("selectedCategory", selectedCategory);
				categoryProductList = productBean.getProductsByCategory(categoryName);
				
				session.setAttribute("categoryProductList", categoryProductList);
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
		RequestDispatcher req = request.getRequestDispatcher(userPath);

		
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
		
		else if(userPath.equals("/deleteProduct")) {
			if(request.getParameter("deleteButton") != null) {
				userPath = "/admin";
				String productToDelete = request.getParameter("productToDelete");
				productBean.deleteProductByName(productToDelete);
				allProductList = productBean.getProducts();
				session.setAttribute("allProductList", allProductList);
				request.getRequestDispatcher("admin").forward(request, response);
				return;
			}
		}
		
		else if(userPath.equals("/addProduct")) {			
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			String productPrice = request.getParameter("productPrice");
			String selectedCategory = request.getParameter("categorySelection");
			
			Product product = new Product(productName, productDescription, productPrice, selectedCategory);
			
			if(product.getProductName().isEmpty() || product.getCategoryName().isEmpty() || product.getProductPrice().isEmpty()) {
				request.getRequestDispatcher("admin").forward(request, response);
				System.out.println("must fill in all product fields");
				return;
			}
			else if(productBean.getProductByName(productName) != null) {
				request.getRequestDispatcher("admin").forward(request, response);
				System.out.println("Product already exists");
				return;
			}
			else {
				productBean.addProduct(product);
				allProductList = productBean.getProducts();
				session.setAttribute("allProductList", allProductList);
				request.setAttribute("product", product);
				request.getRequestDispatcher("admin").forward(request, response);
				System.out.println("added product");
				return;
			}
		}
		
		else if(userPath.equals("/logout")) {
			session.invalidate();
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		else if(userPath.equals("/adminLogin")) {
			String username = request.getParameter("adminUsername");
			String password = request.getParameter("adminPassword");
			String adminLoginResponse = adminBean.checkAdmin(username, password);
			
			if(adminLoginResponse.equals("success")) {
				adminLoginMessage = "success";
				request.setAttribute("adminLoginMessage", adminLoginMessage);
				allProductList = productBean.getProducts();
				session.setAttribute("allProductList", allProductList);
				request.getRequestDispatcher("/admin").forward(request, response);;
				return;
			}
			else {
				adminLoginMessage = "fail";
				request.setAttribute("adminLoginMessage", adminLoginMessage);
				request.getRequestDispatcher("/adminLogin");
				doGet(request, response);
				return;
			}
		}
		
		else if(userPath.equals("/updateQuantity")) {

			String productToUpdate = request.getParameter("productNameToUpdate");
			
			if(request.getParameter("increaseQuantity") != null) {
				cart.getCartItemByName(productToUpdate).increaseQuantity();
				request.getRequestDispatcher("cart").forward(request, response);
				return;
			}
			else if(request.getParameter("decreaseQuantity") != null) {
				cart.getCartItemByName(productToUpdate).decreaseQuantity();
				if(cart.getCartItemByName(productToUpdate).getQuantity() == 0) {
					cart.removeItemByName(productToUpdate);
				}
				request.getRequestDispatcher("cart").forward(request, response);
				return;
			}
			else if(request.getParameter("removeItem") != null) {
				cart.removeItemByName(productToUpdate);
				request.getRequestDispatcher("cart").forward(request, response);
				return;
			}
		}
		
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
