package com.nisum.user.api.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotFoundException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public UserNotFoundException(String message) {
        super(message);
        this.code = "404";
        this.status = HttpStatus.NOT_FOUND;
    }

}