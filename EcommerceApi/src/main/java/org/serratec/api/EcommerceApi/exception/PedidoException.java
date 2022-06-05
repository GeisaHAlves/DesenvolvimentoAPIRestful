package org.serratec.api.EcommerceApi.exception;

public class PedidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public PedidoException() {
        super();
    }

    public PedidoException(String message) {
        super(message);
    }


    public PedidoException(String message, Exception cause) {
        super(message, cause);
    }

    public PedidoException(Exception e) {
        super(e);
    }

}