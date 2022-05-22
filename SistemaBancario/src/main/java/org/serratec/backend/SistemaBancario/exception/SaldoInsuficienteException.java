package org.serratec.backend.SistemaBancario.exception;

public class SaldoInsuficienteException extends Exception {

	private static final long serialVersionUID = -7436645993922383761L;

	public SaldoInsuficienteException() {
		super();
	}

	public SaldoInsuficienteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SaldoInsuficienteException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaldoInsuficienteException(String message) {
		super(message);
	}

	public SaldoInsuficienteException(Throwable cause) {
		super(cause);
	}

	
	
}
