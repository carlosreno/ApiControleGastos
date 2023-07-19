package com.example.apicontrolegastos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletDto(
        @NotBlank
        String name,
        @NotNull
        BigDecimal value,
        @NotNull
        Long customerId
)
{}
