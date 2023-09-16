package com.example.ecommerce.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ecommerce.exceptions.IdNotFoundException;
import com.example.ecommerce.exceptions.UniqueNameException;

@ControllerAdvice
public class ProductAdvice {

	public ResponseEntity<?> createMessage(Exception e) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND + "");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		return createMessage(e);
	}

	@ExceptionHandler(UniqueNameException.class)
	public ResponseEntity<?> uniqueNameExceptionHandler(UniqueNameException e) {
		return createMessage(e);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e)
			throws IOException {

		String provided = e.getMethod();
		List<String> supported = List.of(e.getSupportedMethods());

		String error = provided + " is not one of the supported Http Methods (" + String.join(",", supported) + ")";

		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());

		return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);

	}

}