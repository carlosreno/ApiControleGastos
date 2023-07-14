package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.CustomerType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressMapper {
    public static Address fromDtoToEntity(AddressDto addressDto){
        return Address.builder()
                .address_id(null)
                .rua(addressDto.logradouro())
                .bairro(addressDto.bairro())
                .cidade(addressDto.localidade())
                .numero(addressDto.numero())
                .cep(addressDto.cep())
                .uf(addressDto.uf())
                .customer(null)
                .build();
    }
}
