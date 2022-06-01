package org.serratec.backend.ProjetoBorracharia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value
			= {ClienteException.class }) 
	protected ResponseEntity<Object> naoEncontrado(ClienteException ex) { 
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST); 
		apiError.setMessage(ex.getMessage()); 
		apiError.setDebugMessage(ex.getLocalizedMessage()); 
		return buildResponseEntity(apiError); 
		
	}
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus()); }


}