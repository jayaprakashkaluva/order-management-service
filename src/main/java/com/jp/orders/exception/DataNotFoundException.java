package com.jp.orders.exception;

public class DataNotFoundException extends RuntimeException{

	public String getErrorCode() {
		return errorCode;
	}

	private String errorCode ="10001";

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
	}
	
	
}
