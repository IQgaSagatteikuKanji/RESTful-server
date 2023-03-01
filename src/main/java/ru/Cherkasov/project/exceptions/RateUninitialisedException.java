package ru.Cherkasov.project.exceptions;

public class RateUninitialisedException extends RuntimeException{
    public RateUninitialisedException(String message) {
        super(message);
    }

    public RateUninitialisedException(String message, Throwable cause) {
        super(message, cause);
    }
}
