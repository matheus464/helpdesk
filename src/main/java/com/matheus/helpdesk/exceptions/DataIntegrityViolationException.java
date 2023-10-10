package com.matheus.helpdesk.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    private String msg;

    public DataIntegrityViolationException(String msg){
        this.msg = msg;
    }

}
