package com.example.apicontrolegastos.dto;

import lombok.Builder;

@Builder
public record AddressDto (
         String cep,
         String logradouro,
         String bairro,
         String localidade,
         String uf,
         Long numero,
         Long customerTypeId
){

}
