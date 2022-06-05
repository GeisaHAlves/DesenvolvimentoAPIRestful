package org.serratec.api.EcommerceApi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {

	@ExceptionHandler(value = { ClienteException.class })
	protected ResponseEntity<Object> naoEncontrado(ClienteException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(value = { FuncionarioException.class })
	protected ResponseEntity<Object> naoEncontrado(FuncionarioException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(value = { CategoriaException.class })
	protected ResponseEntity<Object> naoEncontrado(CategoriaException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
		
	}
	
	@ExceptionHandler(value = { PedidoException.class })
	protected ResponseEntity<Object> naoEncontrado(PedidoException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
		
	}
	
	@ExceptionHandler(value = { ProdutoException.class })
	protected ResponseEntity<Object> naoEncontrado(ProdutoException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getLocalizedMessage());
		return buildResponseEntity(apiError);
		
	}
	

	private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}