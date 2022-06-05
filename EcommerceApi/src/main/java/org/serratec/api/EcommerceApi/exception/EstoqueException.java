package org.serratec.api.EcommerceApi.exception;

public class EstoqueException extends Exception{

	private static final long serialVersionUID = 1L;

    public EstoqueException() {
        super();
    }

    public EstoqueException(String message) {
        super(message);
    }


    public EstoqueException(String message, Exception cause) {
        super(message, cause);
    }

    public EstoqueException(Exception e) {
        super(e);
    }
}
