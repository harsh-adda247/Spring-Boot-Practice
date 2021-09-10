package com.example.demo.controller;

import com.example.demo.responseModel.ResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The class to handle validation errors and return them to client in a user friendly form of representation
 */
@RestControllerAdvice
public class RequestExceptionHandlerController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		ResponseModel<?> response = new ResponseModel(HttpStatus.BAD_REQUEST, "Validation Errors", null, errors);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
