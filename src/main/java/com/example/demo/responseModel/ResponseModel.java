package com.example.demo.responseModel;

import org.springframework.http.HttpStatus;

public class ResponseModel<T> {

	private final Integer status;

	private final String message;

	private final String details;
	
	private final T response;


	public ResponseModel(final HttpStatus httpStatus, final String message, final String details, final T response) {
		this.status = httpStatus.value();
		this.message = message;
		this.details = details;
		this.response= response;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public T getResponse() {
		return response;
	}
	
}
