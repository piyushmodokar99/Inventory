package com.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.inventory.model.InvCommonResponse;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<InvCommonResponse> handleExceptions( BadCredentialsException exception, WebRequest webRequest) {
		InvCommonResponse response = new InvCommonResponse();
        response.setStatus(0);
        response.setMsg("Invalid Username or Password.");
        ResponseEntity<InvCommonResponse> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
	
	//ExpiredJwtException
	@ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<InvCommonResponse> handleExceptions(ExpiredJwtException exception) {
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ExpiredJwtException ");
		InvCommonResponse response = new InvCommonResponse();
        response.setStatus(0);
        response.setMsg("Token has been expired.");
        ResponseEntity<InvCommonResponse> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return entity;
    }
}