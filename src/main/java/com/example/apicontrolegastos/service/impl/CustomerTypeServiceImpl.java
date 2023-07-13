package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CustomerTypeMapper;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.repositories.CustomerTypeRepository;
import com.example.apicontrolegastos.service.CustomerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class CustomerTypeServiceImpl implements CustomerTypeService {
    private final CustomerTypeRepository customerTypeRepository;
    @Override
    public CustomerType create(CustomerTypeDto customerTypeDto) {
        CustomerType customerType = CustomerTypeMapper
                .fromDtoToEntity(customerTypeDto);
        return customerTypeRepository.save(customerType);
    }

    @Override
    public MessageDto delete(Long id) {
        var optCustomerType = customerTypeRepository.findById(id);
        return null;
    }
}
