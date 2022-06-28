package com.nisum.user.api.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UnprocesableEntityException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public UnprocesableEntityException(String message) {
        super(message);
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }

}