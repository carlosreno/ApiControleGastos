package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.CustomerType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {
    public static Customer fromDtoToEntity(CustomerType customerType,CustomerDto customerDto){
        return Customer.builder()
                .customerId(null)
                .name(customerDto.name())
                .email(customerDto.email())
                .cpf(customerDto.cpf())
                .tells(customerDto.tells())
                .addresses(customerDto.addresses())
                .age(customerDto.age())
                .customerType(customerType)
                .build();
    }
}
