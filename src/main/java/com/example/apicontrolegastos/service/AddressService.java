package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Address;

import java.util.List;

public interface AddressService {
    Address create(AddressDto addressDto);
    MessageDto delete(Long id);
    List<Address> findAll();
    Address findById(Long id);
    Address update(Long id, AddressDto addressDto);
    Address patch(Long id,AddressDto addressDto);
}
