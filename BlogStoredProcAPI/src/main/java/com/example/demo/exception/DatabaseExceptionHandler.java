package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class DatabaseExceptionHandler {
	 @ExceptionHandler(EntityNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public DatabaseErrorResponse handleResourceNotFound(EntityNotFoundException ex) {
	        return new DatabaseErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	    }

}
