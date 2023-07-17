package com.example.apicontrolegastos.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletDto(
        Long walletId,
        String name,
        BigDecimal value
)
{}
