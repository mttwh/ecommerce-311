package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import beans.CategoryBean;
import beans.CustomerBean;
import beans.ProductBean;
import beans.WishlistBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Cart;
import model.Category;
import model.Customer;
import model.Product;
import model.WishList;

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
					  "/login",
					  "/updateQuantity",
					  "/updateProduct",
					  "/display",
					  "/search",
                                          "/wishlist",
                                          "/moveToCart",
                                          "/removeFromWishlist",
                                          "/addToWishlist"})
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = -8289078937910112382L;
	private List<Product> categoryProductList;
        private List<Product> aListOfProducts;
	private List<Product> allProductList;
        private List<Product> wishProductList;
	private ProductBean productBean;
	private AdminBean adminBean;
	private CategoryBean categoryBean;
	private CustomerBean customerBean;
        private WishlistBean wishlistBean;
	private String adminLoginMessage = null;
	private String addMessage = null;

    public ControllerServlet() {
        super();
        productBean = new ProductBean();
        adminBean = new AdminBean();
        categoryBean = new CategoryBean();
        customerBean = new CustomerBean(); 
        wishlistBean = new WishlistBean();
    }
    
    
    //init method called once upon application deployment.
    //category list will be stored in an application context variable for use across the application
    public void init() throws ServletException {
    	List<Category> categoryList = new ArrayList<>();
    	categoryList = categoryBean.getCategories();
    	getServletContext().setAttribute("categories", categoryList);
        wishProductList = new ArrayList<>();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category selectedCategory;
		HttpSession session = request.getSession();
		
		String userPath = request.getServletPath();


		if(userPath.equals("/category")) {
			
			String categoryName = request.getQueryString();

			if (categoryName != null) 
			{
				selectedCategory = categoryBean.getCategoryByName(categoryName);
				session.setAttribute("selectedCategory", selectedCategory);
				categoryProductList = productBean.getProductsByCategory(categoryName);
				session.setAttribute("categoryProductList", categoryProductList);
			}
		}

		
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("shoppingCart");
		String loginMessage = null;
                

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
			}

			userPath = "/category";
		}
                
                else if(userPath.equals("/addToWishlist")){
                    
                    HttpSession UserSession = request.getSession();
		    Product wishlistProduct = new Product();
                    WishList wishlist = new WishList();
		    String productName = request.getParameter("productName");
                    Customer customer = (Customer) request.getSession().getAttribute("customer");
			
			if(productName != null && !productName.isEmpty())
			{
	
                                Product product = productBean.getProductByName(productName);
                                wishlist.setProduct(product);
                                wishlist.setCustomer(customer);
                                if (wishProductList.size()== 0){
                                  wishProductList.add(product);
  
                                }
                                else{
                                    boolean productNotFound = false;
                                    for(int i = 0; i < wishProductList.size(); i++){
                                        if(wishProductList.get(i).getProductName().equals(productName)){
                                            productNotFound = true;
                                        }
                                     
                                    }
                                    if(!productNotFound){
                                        wishProductList.add(product);
                                    }
                                }
                                
                                UserSession.setAttribute("wishlist", wishProductList);

 			}
		
		userPath = "/category";
                }
                
                else if (userPath.equals("/wishlist")) {
                    
                    HttpSession userSession = request.getSession(); //does this grab new session?
                    Customer customer = (Customer) userSession.getAttribute("customer");
		    String customerUserName = customer.getCustomerUsername();
                    userPath = "/wishlist";

		}
                
                else if (userPath.equals("/removeFromWishlist")){
                    
                    String productName = request.getParameter("productName");
                    for(int i = 0; i < wishProductList.size(); i++){
                            if(wishProductList.get(i).getProductName().equals(productName)){
                                wishProductList.remove(i);
                            }
                    }
                    userPath = "/wishlist";
                    
                }
                
                //wishlist moveToCart
                else if(userPath.equals("/moveToCart")) {
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
                                for(int i = 0; i < wishProductList.size(); i++){
                                    if(wishProductList.get(i).getProductName().equals(productName)){
                                        wishProductList.remove(i);
                                    }
                                }
			}

			userPath = "/wishlist";
                                
                    } 
                
                else if(userPath.equals("/deleteProduct")) {
			String productToDelete = request.getParameter("productToDelete");
			productBean.deleteProductByName(productToDelete);
			allProductList = productBean.getProducts();
			session.setAttribute("allProductList", allProductList);
			request.getRequestDispatcher("/admin").forward(request, response);
			return;
		}
		
		else if(userPath.equals("/addProduct")) {			
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			String productPrice = request.getParameter("productPrice");
			String selectedCategory = request.getParameter("categorySelection");

			Product product = new Product(productName, productDescription, productPrice, selectedCategory);
			
			if(product.getProductName().isEmpty() || product.getCategoryName().isEmpty() || product.getProductPrice().isEmpty()) {
				addMessage = "empty-fields";
				request.setAttribute("addMessage", addMessage);
			}
			else if(Double.parseDouble(productPrice) < 0 || Double.parseDouble(productPrice) > 500) {
				addMessage = "invalid-price";
				request.setAttribute("addMessage", addMessage);
			}
			else if(productBean.getProductByName(productName) != null) {
				addMessage = "exists";
				request.setAttribute("addMessage", addMessage);
			}
			else {
				productBean.addProduct(product);
				allProductList = productBean.getProducts();
				session.setAttribute("allProductList", allProductList);
				request.setAttribute("product", product);
			}
			request.getRequestDispatcher("/admin").forward(request, response);
			return;
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
				request.getRequestDispatcher("/admin").forward(request, response);
				return;
			}
			else {
				adminLoginMessage = "fail";
				request.setAttribute("adminLoginMessage", adminLoginMessage);
				doGet(request, response);
				return;
			}
		}
		
		else if(userPath.equals("/updateQuantity")) {
			String productToUpdate = request.getParameter("productNameToUpdate");
			
			if(request.getParameter("increaseQuantity") != null) {
				cart.getCartItemByName(productToUpdate).increaseQuantity();
			}
			else if(request.getParameter("decreaseQuantity") != null) {
				cart.getCartItemByName(productToUpdate).decreaseQuantity();
				if(cart.getCartItemByName(productToUpdate).getQuantity() == 0) {
					cart.removeItemByName(productToUpdate);
				}
			}
			else if(request.getParameter("removeItem") != null) {
				cart.removeItemByName(productToUpdate);
			}
			
			request.getRequestDispatcher("/cart").forward(request, response);
			return;
		}
		
		else if(userPath.equals("/updateProduct")) {
			String productToUpdateName = request.getParameter("productToUpdate");
			Product productToUpdate = productBean.getProductByName(productToUpdateName);
			request.setAttribute("productToUpdate", productToUpdate);

			if(request.getParameter("updateProductButton") != null) {
				String newProductName = request.getParameter("newProductName");
				String newProductDescription = request.getParameter("newProductDescription");
				String newProductPrice = request.getParameter("newProductPrice");
				String newProductCategory = request.getParameter("newProductCategory");
				String oldProductName = request.getParameter("oldProductName");
				
				Product newProduct = new Product(newProductName, newProductDescription, newProductPrice, newProductCategory);
				productBean.replaceProduct(newProduct, oldProductName);
				
				request.getRequestDispatcher("/admin").forward(request, response);
				return;
			}
}
		
		else if(userPath.equals("/admin")) {
			allProductList = productBean.getProducts();
			session.setAttribute("allProductList", allProductList);
		}
		
		else if(userPath.equals("/login")) {
			String username = request.getParameter("customerUsername");
			String password = request.getParameter("customerPassword");
                        
			
			if(customerBean.validateCredentials(username, password)) {
				HttpSession userSession = request.getSession(true); //does this grab new session?
				Customer customer = customerBean.getCustomerByUsername(username);
				userSession.setAttribute("customer", customer);
                                //session.setAttribute("user", customer);
				loginMessage = "success";
				request.setAttribute("message", loginMessage);
			}
			else {
				loginMessage = "fail";
				request.setAttribute("message", loginMessage);
			}
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		else if(userPath.equals("/register")) {
			String username = request.getParameter("customerUsername");
			String password = request.getParameter("customerPassword");
			String fullName = request.getParameter("customerName");
			String email = request.getParameter("customerEmail");
			
			Customer customer = new Customer(username, password, fullName, email);
			
			if(username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
				request.getRequestDispatcher("/register").forward(request, response);
				return;
			}
			else {
				customerBean.registerCustomer(customer);
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}
		}
		
		else if(userPath.equals("/search")) {
			List<Product> prodList = productBean.getProducts();
			String searchedProduct = request.getParameter("searchedProduct");
			Category category = productBean.serachProductByName(prodList, searchedProduct);
			if(category == null) {
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}
			String catUrl = "/category?" + category;
			categoryProductList = productBean.getProductsByCategory(category.getCategoryName());
			session.setAttribute("selectedCategory", category);
			session.setAttribute("categoryProductList", categoryProductList);
			request.getRequestDispatcher(catUrl).forward(request, response);
			return;
		}
		
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}