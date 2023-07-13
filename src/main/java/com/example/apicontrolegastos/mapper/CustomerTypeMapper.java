package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.model.CustomerType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTypeMapper {
    public static CustomerType fromDtoToEntity(CustomerTypeDto customerTypeDto){
        return CustomerType.builder()
                .name(customerTypeDto.name())
                .build();
    }
}
