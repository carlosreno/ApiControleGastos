package com.example.apicontrolegastos.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerTypeDto(
        @NotBlank
        String name
) {}
