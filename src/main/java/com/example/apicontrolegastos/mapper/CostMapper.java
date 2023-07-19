package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.CostDto;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Cost;
import com.example.apicontrolegastos.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CostMapper {
    public static Cost fromDtoToEntity(Customer customer,Long id,CostDto costDto){
        return Cost.builder()
                .costId(id)
                .customer(customer)
                .name(costDto.name())
                .value(costDto.value())
                .initialDate(costDto.initialDate())
                .finalDate(costDto.finalDate())
                .build();
    }
}
