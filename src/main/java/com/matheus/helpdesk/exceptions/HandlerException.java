package com.matheus.helpdesk.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.HttpURLConnection;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartException> objectNotFound(ObjectNotFoundException exp, HttpServletRequest request){
        StandartException error = new StandartException(System.currentTimeMillis(), HttpStatus.NO_CONTENT.ordinal(), exp.getMessage(),
                "Object not Found", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
