package com.jacaranda.cart;

import java.util.ArrayList;


public class Cart {
	
	private ArrayList<CartItem> cartItems = new ArrayList<>();
	final double DECIMALES = 100.0;
	
	public Cart() {
		super();
	}
	
	public int getQuantityArticles() {
		int quantity = 0;
		if(this.cartItems!=null) {
			for(CartItem item: cartItems) {
				quantity += item.getQuantity();
			}
		}
		return quantity;
	}
	
	
	public double getTotalPrice() {
		double totalPrice = 0;
		if(this.cartItems!=null) {
			for(CartItem item : cartItems) {
				totalPrice += item.getPriceItem();
			}
		}
		return Math.round(totalPrice*DECIMALES)/DECIMALES;
	}
	
	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(ArrayList<CartItem> cart) {
		this.cartItems = cart;
	}

	public void emptyCart() {
		this.cartItems.clear();
	}
	
	public void addCartItem(CartItem cartItem) throws CartExpection {
		if(cartItem==null) {
			throw new CartExpection("Item nulo");
		}
		int index = this.cartItems.indexOf(cartItem);
		if(index==-1) {
			cartItems.add(cartItem);
		}else {
			cartItems.get(index).setQuantity(cartItem.getQuantity());
		}
	}
	
	public boolean deleteCartItem(CartItem cartItem) throws CartExpection {
		if(cartItem==null) {
			throw new CartExpection("Item nulo");
		}
		int index = this.cartItems.indexOf(cartItem);
		if(index==-1) {
			throw new CartExpection("No existe el item en el carrito");
		}
		return cartItems.remove(cartItem);
	}
	
	public void modifyQuantityCartItem(CartItem cartItem, int quantity, int maxStockItem) throws CartExpection {
		if(cartItem==null) {
			throw new CartExpection("Item nulo");
		}
		if(quantity<1 || quantity>maxStockItem) {
			throw new CartExpection("Cantidad erronea");
		}
		int index = this.cartItems.indexOf(cartItem);
		if(index==-1) {
			throw new CartExpection("No existe el item en el carrito");
		}
		this.cartItems.get(index).setQuantity(quantity);
	}
}