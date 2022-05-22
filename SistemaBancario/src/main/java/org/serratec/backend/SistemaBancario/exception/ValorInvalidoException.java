package org.serratec.backend.SistemaBancario.exception;

public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = -7110310339701841224L;

	public ValorInvalidoException() {
		super();
	}

	public ValorInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValorInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorInvalidoException(String message) {
		super(message);
	}

	public ValorInvalidoException(Throwable cause) {
		super(cause);
	}

	
	
}
