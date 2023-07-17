package com.example.apicontrolegastos.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record MessageDto(
        List<String> messages,
        HttpStatus httpStatus,
        Integer statusCode
) {
}
