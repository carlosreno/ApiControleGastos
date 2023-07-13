package com.example.apicontrolegastos.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record MessageDto(
        String message,
        HttpStatus httpStatus,
        Integer statusCode
) {
}
