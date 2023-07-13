package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.CustomerType;
import org.aspectj.bridge.Message;

public interface CustomerTypeService {
    CustomerType create(CustomerTypeDto customerTypeDto);
    MessageDto delete(Long id);
}
