package com.example.apicontrolegastos.exception.handler;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.BusinessException;
import com.example.apicontrolegastos.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDto> notFoundException(NotFoundException n){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .message(n.getMessage())
                .build());
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageDto> businessException(BusinessException n){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .message(n.getMessage())
                .build());
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDto> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException n){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .message(n.getMessage())
                .build());
    }
}
