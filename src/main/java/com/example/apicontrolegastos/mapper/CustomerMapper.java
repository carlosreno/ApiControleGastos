package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.CustomerType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {
    public static Customer fromDtoToEntity(Long id,CustomerType customerType,CustomerDto customerDto){
        return Customer.builder()
                .customerId(id)
                .name(customerDto.name())
                .email(customerDto.email())
                .cpf(customerDto.cpf())
                .age(customerDto.age())
                .customerType(customerType)
                .build();
    }
}
