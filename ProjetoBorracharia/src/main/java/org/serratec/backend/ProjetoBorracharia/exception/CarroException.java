package org.serratec.backend.ProjetoBorracharia.exception;

public class CarroException extends Exception{

	private static final long serialVersionUID = 2853203296967753118L;

	public CarroException() {
		super();
	}

	public CarroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CarroException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarroException(String message) {
		super(message);
	}

	public CarroException(Throwable cause) {
		super(cause);
			}

	
}
