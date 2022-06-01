package org.serratec.backend.ProjetoBorracharia.exception;

public class UsuarioException extends Exception {

	private static final long serialVersionUID = -7757105187718162591L;

	public UsuarioException() {
		super();
	}

	public UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioException(String message) {
		super(message);
	}

	public UsuarioException(Throwable cause) {
		super(cause);
	}

}
