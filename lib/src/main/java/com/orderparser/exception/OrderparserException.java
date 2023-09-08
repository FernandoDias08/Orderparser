package com.orderparser.exception;

public class OrderparserException extends RuntimeException {

	private static final long serialVersionUID = 4603405815875633975L;

	public OrderparserException() {
		super();
	}

	public OrderparserException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderparserException(String message) {
		super(message);
	}

	public OrderparserException(Throwable cause) {
		super(cause.getMessage());
	}

}