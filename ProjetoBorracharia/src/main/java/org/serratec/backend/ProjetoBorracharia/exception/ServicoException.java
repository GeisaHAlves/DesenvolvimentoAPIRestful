package org.serratec.backend.ProjetoBorracharia.exception;

public class ServicoException extends Exception{

	private static final long serialVersionUID = 4564196039923117925L;

	public ServicoException() {
		super();
	}

	public ServicoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServicoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServicoException(String message) {
		super(message);
			}

	public ServicoException(Throwable cause) {
		super(cause);
			}

	
}
