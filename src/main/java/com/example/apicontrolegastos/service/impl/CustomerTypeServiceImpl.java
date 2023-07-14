package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CustomerTypeMapper;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.repositories.CustomerTypeRepository;
import com.example.apicontrolegastos.service.CustomerTypeService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        verifyIfExistCustomerType(id);
        return MsgStandard.msgStandardOk("deletado");
    }

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }

    private void verifyIfExistCustomerType(Long id) {
        var optCustomerType = customerTypeRepository.findById(id);
        optCustomerType.ifPresentOrElse(
                customerTypeRepository::delete,
                ()-> {
                throw new NotFoundException("id não existe");
                }
        );
    }
}
