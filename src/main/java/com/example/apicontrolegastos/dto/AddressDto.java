package com.example.apicontrolegastos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressDto (
        @NotBlank
         String cep,
        @NotBlank
        String uf,
        @NotBlank
         String rua,
        @NotBlank
         String bairro,
        @NotBlank
         String cidade,
        @NotNull
        Long numero,
        @NotNull
        Long customerId
){

}
