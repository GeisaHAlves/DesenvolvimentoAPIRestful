package org.serratec.java2backend.projetopetshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(PetShopException.class)
	public ResponseEntity<String> trataTodoNotFound(PetShopException exception) {
		String msg = String.format("O todo com o ID %d não foi encontrado", exception.getId());
		return ResponseEntity.notFound().header("x-erro-msg", msg).header("x-erro-code", "CONTEUDO NOT FOUND")
				.header("x-erro-value", exception.getId().toString()).build();
	}
}
