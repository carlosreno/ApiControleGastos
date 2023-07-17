package com.example.apicontrolegastos.exception.handler;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.BusinessException;
import com.example.apicontrolegastos.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDto> notFoundException(NotFoundException n){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .messages(Collections.singletonList(n.getMessage()))
                .build());
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageDto> businessException(BusinessException n){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .messages(Collections.singletonList(n.getMessage()))
                .build());
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDto> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException n){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                        .messages(Collections.singletonList(n.getMessage()))
                .build());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageDto> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage())
                .toList();

        return ResponseEntity.badRequest().body(MessageDto.builder()
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .messages(errors)
                .build());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> methodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // Criar uma lista de mensagens de erro usando stream
        List<String> errorMessages = fieldErrors.stream()
                .map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage())
                .toList();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(MessageDto.builder()
                .messages(errorMessages)
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .build());
    }
}
