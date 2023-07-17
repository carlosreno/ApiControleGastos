package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer create(CustomerDto customerDto);
    MessageDto delete(Long id);
    List<Customer> findAll();
    Customer findById(Long id);
    Customer update(Long id, CustomerDto dto);
    Customer patch(Long id, CustomerDto dto);
}
