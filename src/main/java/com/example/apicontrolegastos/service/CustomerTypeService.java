package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.CustomerType;

import java.util.List;

public interface CustomerTypeService {
    CustomerType create(CustomerTypeDto customerTypeDto);
    MessageDto delete(Long id);
    List<CustomerType> findAll();
}
