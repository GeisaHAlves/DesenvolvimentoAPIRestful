package org.serratec.backend.ProjetoBiblioteca.exception;

public class LivroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradoException() {
		super();
	}

	public LivroNaoEncontradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LivroNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public LivroNaoEncontradoException(String message) {
		super(message);
	}

	public LivroNaoEncontradoException(Throwable cause) {
		super(cause);
	}
	
	

}
