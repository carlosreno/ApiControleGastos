package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.repositories.AddressRepository;
import com.example.apicontrolegastos.service.AddressService;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    @Override
    public Address create(AddressDto addressDto) {
        Customer customer = customerService.findById(addressDto.customerId());
        AddressMapper()
        return addressRepository.save(address);
    }

    @Override
    public MessageDto delete(Long id) {
        Address address = verifyIfExistAddressWithId(id);
        addressRepository.deleteById(address.getAddressId());
        return MsgStandard.msgStandardOk("deleted");
    }

    private Address verifyIfExistAddressWithId(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(()->new NotFoundException("address with id not exist"));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return verifyIfExistAddressWithId(id);
    }

    @Override
    public Address update(Address address) {
        Address addressBd = verifyIfExistAddressWithId(address.getAddressId());
        BeanUtils.copyProperties(address,addressBd);
        return addressRepository.save(addressBd);
    }

    @Override
    public Address patch(Address address) {
        Address addressBd = verifyIfExistAddressWithId(address.getAddressId());
        Address addressUpdate = Address.builder()
                .addressId(addressBd.getAddressId())
                .cep(address.getCep()!=null ? address.getCep() : addressBd.getCep())
                .rua(address.getRua()!=null ? address.getRua() : addressBd.getRua())
                .bairro(address.getBairro()!=null ? address.getBairro() : addressBd.getBairro())
                .numero(address.getNumero()!=null ? address.getNumero() : addressBd.getNumero())
                .cidade(address.getCidade()!=null ? address.getCidade() : addressBd.getCidade())
                .uf(address.getUf()!=null ? address.getUf() : addressBd.getUf())
                .customer(addressBd.getCustomer())
                .build();
        return addressRepository.save(addressUpdate);
    }
}
