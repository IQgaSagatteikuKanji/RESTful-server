package ru.Cherkasov.project.exceptions;

public class NotExistingCurrencyException extends RuntimeException{
    public NotExistingCurrencyException(String message) {
        super(message);
    }

    public NotExistingCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
