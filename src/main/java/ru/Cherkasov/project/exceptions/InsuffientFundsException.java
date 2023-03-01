package ru.Cherkasov.project.exceptions;

public class InsuffientFundsException extends RuntimeException{
    public InsuffientFundsException(String message) {
        super(message);
    }

    public InsuffientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
