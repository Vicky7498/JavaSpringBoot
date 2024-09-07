package com.infy.utility;

import com.infy.exception.InfyBankException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @Autowired
    Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InfyBankException.class)
    public ResponseEntity<ErrorInfo> infyBankExceptionHandler(InfyBankException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        errorInfo.setTimeStamp(LocalDateTime.now());
        errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimeStamp(LocalDateTime.now());
        String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));
        errorInfo.setErrorMessage(errorMsg);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> pathExceptionHandler(ConstraintViolationException constraintViolationException) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorMessage(constraintViolationException.getConstraintViolations().stream().map(x -> x.getMessage()).collect(Collectors.joining(", ")));
        errorInfo.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
