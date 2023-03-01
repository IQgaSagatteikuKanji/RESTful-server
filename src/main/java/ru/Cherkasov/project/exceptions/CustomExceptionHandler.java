package ru.Cherkasov.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UserNotAdminException.class})
    public ResponseEntity<Object> handleUserNotAdminException(UserNotAdminException e){
        HttpStatus badRequest = HttpStatus.UNAUTHORIZED;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {InsuffientFundsException.class})
    public ResponseEntity<Object> handleInsufficientFundsException(InsuffientFundsException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {RateUninitialisedException.class})
    public ResponseEntity<Object> handleRateUninitialisedException(RateUninitialisedException e){
        HttpStatus badRequest = HttpStatus.SERVICE_UNAVAILABLE;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {NotExistingCurrencyException.class})
    public ResponseEntity<Object> handleNotExistingCrrencyException(NotExistingCurrencyException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponse apiException = new ApiExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
