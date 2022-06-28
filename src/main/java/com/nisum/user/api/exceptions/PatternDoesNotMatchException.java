package com.nisum.user.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@Data
public class PatternDoesNotMatchException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	private String code;

    private HttpStatus status;
	
	public PatternDoesNotMatchException(String message) {
		super(message);
		this.code="400";
		this.status = HttpStatus.BAD_REQUEST;
	}	
}
