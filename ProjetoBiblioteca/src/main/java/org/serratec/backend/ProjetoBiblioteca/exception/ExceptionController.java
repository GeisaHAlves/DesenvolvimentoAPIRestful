package org.serratec.backend.ProjetoBiblioteca.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value
			= {LivroNaoEncontradoException.class }) 
	protected ResponseEntity<Object> naoEncontrado(LivroNaoEncontradoException ex) { 
		ApiBibliotecaError apiError = new ApiBibliotecaError(HttpStatus.BAD_REQUEST); 
		apiError.setMessage(ex.getMessage()); 
		apiError.setDebugMessage(ex.getLocalizedMessage()); 
		return buildResponseEntity(apiError); 
		
	}
	private ResponseEntity<Object> buildResponseEntity(ApiBibliotecaError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus()); }



}
