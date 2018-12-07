/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Cart;
import model.CartItem;
import model.Customer;
/**
 *
 * @author hrai36
 */
public class WishList {
    
    //private ArrayList<Product> wishList;
    public Product product; 
    public Customer customer;
	
	public WishList(Product product, Customer customer) {
		this.product = product;
                this.customer = customer;
	}
        
        public WishList(){
            
        }
        
        //move item from wishlist to shopping cart
        /*public void movetoCart(Product product, List<CartItem> cartItems, int quantity, Cart cart){
            
            boolean itemPresent = false;
            
            for(int i = 0; i < cartItems.size (); i++){
                if(cartItems.get(i).getProductName() == product.getProductName()){
                    
                    itemPresent = true;
                    System.out.println("WishList item already present in cart");
                    
                }
            }
            
            if(!itemPresent){
                CartItem ci = new CartItem(product);
                ci.setQuantity(quantity);
                cartItems.add(ci);
                cart.calculateTotal();
            }
            
        }*/
	
        //add to wishlist
	public void addItem(Product product, Customer customer) {
            
            /*boolean itemPresent = false;
		
		for(int i = 0; i < wishList.size(); i++)
		{
			if(wishList.get(i).getProductName() == product.getProductName()) {
				
				itemPresent = true;
				System.out.println("Wishlist item already present in wishlist");
			}
		}
		
		if(!itemPresent) 
		{
                    wishList.add(product);
		}*/
	}
	
	public Product getProduct() {
		return product;
	}
        
        public void setProduct(Product product) {
		this.product = product;
	}
        
        public Customer getCustomer() {
		return customer;
	}
        
        public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	



	
    
}

