package com.example.ecommerce.exceptions;

public class UniqueNameException extends Exception {

	private static final long serialVersionUID = 1L;

	public UniqueNameException(String msg) {
		super(msg);
	}
}
