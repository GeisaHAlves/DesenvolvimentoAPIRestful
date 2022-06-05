package org.serratec.api.EcommerceApi.exception;

public class APIException extends Exception {

    private static final long serialVersionUID = 1L;

    public APIException(String message) {
        super(message);
    }
}