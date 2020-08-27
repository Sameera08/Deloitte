package com.deloitte.org.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deloitte.org.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(InvalidUrlException.class)
	public ResponseEntity<ErrorResponse> assertionException(Exception e) {
		final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setCode(HttpStatus.NOT_FOUND);
		errorResponse.setError(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> alreadyExists(Exception e) {
		final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setCode(HttpStatus.ALREADY_REPORTED);
		errorResponse.setError(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
	}
}
