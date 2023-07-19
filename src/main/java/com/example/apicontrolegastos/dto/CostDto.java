package com.example.apicontrolegastos.dto;

import com.example.apicontrolegastos.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CostDto(
        @NotBlank
        String name,
        @NotNull
        BigDecimal value,
        @NotBlank
        LocalDate initialDate,
        @NotBlank
        LocalDate finalDate,
        @NotNull
        Long customerId
)
{}
