package com.example.apicontrolegastos.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }
}