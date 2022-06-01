package org.serratec.backend.ProjetoBorracharia.exception;

public class EmailException extends Exception{

	private static final long serialVersionUID = -1602548273179361603L;

	public EmailException() {
		super();
	}

	public EmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}

	public EmailException(Throwable cause) {
		super(cause);
	}

	
}
