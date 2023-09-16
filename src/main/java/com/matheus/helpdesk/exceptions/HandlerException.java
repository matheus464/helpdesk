package com.matheus.helpdesk.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandartException> objectNotFoundException(ObjectNotFound exp, HttpServletRequest request){
        StandartException error = new StandartException(System.currentTimeMillis(), HttpStatus.NO_CONTENT.ordinal(), "Object not found!",
                exp.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
