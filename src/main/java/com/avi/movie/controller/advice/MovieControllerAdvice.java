package com.avi.movie.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.avi.movie.exception.CustomErrorResponse;
import com.avi.movie.exception.MovieNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
*<h1>The aim of this Controller Advice class is to handler all application exception at single point HTTP.</h1>
*@author avinashsingh
*/   
@ControllerAdvice
public class MovieControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	* The aim of this method is to handle MovieNotFoundException in application.
	* @return ResponseEntity this is the ResponseEntity that holds error message,time,http status 404
	*/
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> movieNotFound(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
    
    /**
	* The aim of this method is to handle RuntimeException in application.
	* @return ResponseEntity this is the ResponseEntity that holds error message,time,http status 500
	*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> exception(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}