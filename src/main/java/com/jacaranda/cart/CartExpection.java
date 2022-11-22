package com.jacaranda.cart;

public class CartExpection extends Exception {

	public CartExpection() {
	}

	public CartExpection(String message) {
		super(message);
	}

	public CartExpection(Throwable cause) {
		super(cause);
	}

	public CartExpection(String message, Throwable cause) {
		super(message, cause);
	}

	public CartExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
