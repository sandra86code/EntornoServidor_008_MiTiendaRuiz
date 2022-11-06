package com.jacaranda.model;

public class PurchaseException extends Exception {

	public PurchaseException() {
		
	}

	public PurchaseException(String message) {
		super(message);
		
	}

	public PurchaseException(Throwable cause) {
		super(cause);
		
	}

	public PurchaseException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PurchaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
