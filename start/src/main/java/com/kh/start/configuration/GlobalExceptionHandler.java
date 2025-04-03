package com.kh.start.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.exception.InvalidUserRequestException;
import com.kh.start.exception.MemberIdDuplicateException;
import com.kh.start.exception.PasswordMisMatchedException;
import com.kh.start.exception.TokenExpireException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private ResponseEntity<Map<String, String>> makeResponseEntity(RuntimeException e, HttpStatus status) {
		Map<String, String> error = new HashMap<>();
		error.put("error-message",e.getMessage());		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(InvalidUserRequestException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserError(InvalidUserRequestException e) {
		/*
		Map<String, String> error = new HashMap<>();
		error.put("error-message",e.getMessage());		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		*/
		return makeResponseEntity(e, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(PasswordMisMatchedException.class)
	public ResponseEntity<Map<String, String>> handlePasswordMisMatched(PasswordMisMatchedException e) {
		/*
		Map<String, String> error = new HashMap<>();
		error.put("error-message",e.getMessage());		
		return ResponseEntity.badRequest().body(error);
		*/
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TokenExpireException.class)
	public ResponseEntity<Map<String, String>> handleTokenExpire(TokenExpireException e) {
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MemberIdDuplicateException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateMemberId(MemberIdDuplicateException e) {
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<?> handleAuthentication(CustomAuthenticationException e) {
		Map<String, String> error = new HashMap<>();
		error.put("error-message",e.getMessage());		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		/*
		List list = e.getBindingResult().getFieldErrors();
		
		for(int i = 0; i < list.size(); i++) {
			log.info("예외가 발생한 필드명 : {}, 이유 : {}",
					((FieldError)list.get(i)).getField(),
					((FieldError)list.get(i)).getDefaultMessage()
			);		
		}
		*/
		e.getBindingResult().getFieldErrors().forEach(error -> 
			errors.put(error.getField(), error.getDefaultMessage())
		);		
		return ResponseEntity.badRequest().body(errors);
	}
	
}
