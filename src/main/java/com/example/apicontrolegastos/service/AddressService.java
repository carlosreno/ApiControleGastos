package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Address;

import java.util.List;

public interface AddressService {
    Address create(Address address);
    MessageDto delete(Long id);
    List<Address> findAll();
    Address findById(Long id);
    Address update(Address address);
    Address patch(Address address);
}
