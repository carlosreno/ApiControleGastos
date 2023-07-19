package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.AddressMapper;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.repositories.AddressRepository;
import com.example.apicontrolegastos.service.AddressService;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    @Override
    public Address create(AddressDto addressDto) {
        Customer customer = getCustomer(addressDto.customerId());
        Address address = AddressMapper.fromDtoToEntity(customer,addressDto,null);
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
    public Address update(Long id,AddressDto addressDto) {
        Address addressBd = verifyIfExistAddressWithId(id);
        Address newAddress = AddressMapper
                .fromDtoToEntity(getCustomer(addressDto.customerId()),addressDto,addressBd.getAddressId());
        return addressRepository.save(newAddress);
    }

    @Override
    public Address patch(Long id,AddressDto addressDto) {
        Address addressBd = verifyIfExistAddressWithId(id);
        Address addressUpdate = Address.builder()
                .addressId(id)
                .cep(addressDto.cep()!=null ? addressDto.cep() : addressBd.getCep())
                .rua(addressDto.rua()!=null ? addressDto.rua() : addressBd.getRua())
                .bairro(addressDto.bairro()!=null ? addressDto.bairro() : addressBd.getBairro())
                .numero(addressDto.numero()!=null ? addressDto.numero() : addressBd.getNumero())
                .cidade(addressDto.cidade()!=null ? addressDto.cidade() : addressBd.getCidade())
                .uf(addressDto.uf()!=null ? addressDto.uf() : addressBd.getUf())
                .customer(addressBd.getCustomer())
                .build();
        return addressRepository.save(addressUpdate);
    }

    private Customer getCustomer(Long customerId) {
        return customerService.findById(customerId);
    }
}
