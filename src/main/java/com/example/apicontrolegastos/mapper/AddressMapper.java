package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressMapper {
    public static Address fromDtoToEntity(Customer customer, AddressDto addressDto,Long id){
        return Address.builder()
                .addressId(id)
                .customer(customer)
                .cep(addressDto.cep())
                .rua(addressDto.rua())
                .bairro(addressDto.bairro())
                .numero(addressDto.numero())
                .cidade(addressDto.cidade())
                .uf(addressDto.uf())
                .build();
    }
}
