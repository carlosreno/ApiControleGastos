package com.example.apicontrolegastos.dto;

import com.example.apicontrolegastos.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
