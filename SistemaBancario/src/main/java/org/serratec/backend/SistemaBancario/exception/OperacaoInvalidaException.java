package org.serratec.backend.SistemaBancario.exception;

public class OperacaoInvalidaException extends Exception {

	private static final long serialVersionUID = 4577414571597366530L;

	public OperacaoInvalidaException() {
		super();
	}

	public OperacaoInvalidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperacaoInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperacaoInvalidaException(String message) {
		super(message);
	}

	public OperacaoInvalidaException(Throwable cause) {
		super(cause);
	}

}
