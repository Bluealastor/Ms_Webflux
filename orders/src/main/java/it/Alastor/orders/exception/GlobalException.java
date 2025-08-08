package it.Alastor.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedException> AlreadyFoundException(UnauthorizedException exception){
        return new ResponseEntity<>(new ExceptionErrorMessage("Element Already Found",exception.getMessage()), HttpStatus.FOUND);
    }
}
