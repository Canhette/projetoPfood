package br.com.canhette.pfood.application.service;

public class AplicationServiceException extends RuntimeException{

    public AplicationServiceException(String message) {
        super(message);
    }

    public AplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AplicationServiceException(Throwable cause) {
        super(cause);
    }
}
