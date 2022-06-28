package com.nisum.user.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.nisum.user.api.exceptions.*;
import com.nisum.user.api.model.dto.CustomResponseException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(value = PatternDoesNotMatchException.class)
    public ResponseEntity<CustomResponseException> patternDoesNotMatchHandler(PatternDoesNotMatchException ex){
        CustomResponseException exceptionDto = CustomResponseException.builder().code("400").message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnprocesableEntityException.class)
    public ResponseEntity<CustomResponseException> userAlreadyExistExceptionHandler(UnprocesableEntityException ex){
        CustomResponseException exceptionDto = CustomResponseException.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(value = NotAuthorizedException.class)
    public ResponseEntity<CustomResponseException> notAuthorizedException(NotAuthorizedException ex){
        CustomResponseException exceptionDto = CustomResponseException.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<CustomResponseException> UserNotFoundException(UserNotFoundException ex){
        CustomResponseException exceptionDto = CustomResponseException.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
    

}
