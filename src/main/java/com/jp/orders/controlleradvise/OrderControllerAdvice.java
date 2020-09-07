package com.jp.orders.controlleradvise;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jp.orders.exception.DataNotFoundException;
import com.jp.orders.vo.Error;

@RestControllerAdvice
public class OrderControllerAdvice {

	@ExceptionHandler(DataNotFoundException.class)
	public Error handleDataNotFoundException(DataNotFoundException ex) {
		Error error = new Error();
		error.setCode(ex.getErrorCode());
		error.setMessage(ex.getMessage());
		return error;
	}
}
