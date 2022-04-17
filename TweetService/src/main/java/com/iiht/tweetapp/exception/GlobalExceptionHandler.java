package com.iiht.tweetapp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iiht.tweetapp.model.ResponseForException;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    log.error("errors {}",errors);
	    return errors;
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ResponseForException> invalidTokenException(InvalidTokenException invalidTokenException) {
		log.error(invalidTokenException.getLocalizedMessage());
		return new ResponseEntity<>(
				new ResponseForException(invalidTokenException.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED),
				HttpStatus.UNAUTHORIZED);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RetryableException.class)
	public ResponseEntity<ResponseForException> microServiceUnavailableException( ) {
		log.error("user service unavailable");
		return new ResponseEntity<>(
				new ResponseForException("MicroServiceUnavailable", LocalDateTime.now(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(UnauthorizedException ex) {
		log.error(ex.getLocalizedMessage());
		return new ResponseEntity<>(
				new ResponseForException("Un Authorized, Login Again ...", LocalDateTime.now(), HttpStatus.UNAUTHORIZED),
				HttpStatus.UNAUTHORIZED);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<Object> handleUserExistsExceptions(UserExistsException ex) {
		log.error(ex.getLocalizedMessage());
		return new ResponseEntity<>(
				new ResponseForException("User Already Exsists", LocalDateTime.now(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex) {
		log.error(ex.getLocalizedMessage());
		return new ResponseEntity<>(
				new ResponseForException("User Not Exsists", LocalDateTime.now(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception ex) {
		log.error("{}",ex.getStackTrace());
		log.error("{}",ex);
		return new ResponseEntity<>(
				new ResponseForException(ex.getLocalizedMessage(), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
