package com.example.apicontrolegastos.dto;

import com.example.apicontrolegastos.model.Customer;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CostDto(
        String name,
        BigDecimal value,
        LocalDate initialDate,
        LocalDate finalDate,
        Customer customer
)
{}
