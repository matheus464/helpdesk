package com.matheus.helpdesk.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartException> DataIntegrityViolationException(DataIntegrityViolationException exp, HttpServletRequest request){
        StandartException error = new StandartException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de dados!",
                exp.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartException> ValidationError(MethodArgumentNotValidException exp, HttpServletRequest request){
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "Erro na validação dos campos!", request.getRequestURI());

        for(FieldError x: exp.getBindingResult().getFieldErrors()){
            error.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
