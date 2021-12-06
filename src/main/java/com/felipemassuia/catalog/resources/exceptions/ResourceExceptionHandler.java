package com.felipemassuia.catalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipemassuia.catalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<StandadError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		
		StandadError error = new StandadError();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setTimestamp(Instant.now());
		error.setURI(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
