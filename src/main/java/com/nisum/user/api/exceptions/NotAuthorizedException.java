package com.nisum.user.api.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class NotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 7242752513777907005L;

	private String code;

    private HttpStatus status;

    public NotAuthorizedException() {
        super("No está autorizado a realizar esta consulta");
        this.code = "401";
        this.status = HttpStatus.UNAUTHORIZED;
    }
}
