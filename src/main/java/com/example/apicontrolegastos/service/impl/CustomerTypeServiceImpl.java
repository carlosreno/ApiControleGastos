package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.BusinessException;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CustomerTypeMapper;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.repositories.CustomerTypeRepository;
import com.example.apicontrolegastos.service.CustomerTypeService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerTypeServiceImpl implements CustomerTypeService {
    private final CustomerTypeRepository customerTypeRepository;
    @Override
    public CustomerType create(CustomerTypeDto customerTypeDto) {
        verifyIfExistCustomerTypeWithName(customerTypeDto.name());
        CustomerType customerType = CustomerTypeMapper
                .fromDtoToEntity(customerTypeDto);
        return customerTypeRepository.save(customerType);
    }

    @Override
    public MessageDto delete(Long id) {
        CustomerType customerType = verifyIfExistCustomerType(id);
        customerTypeRepository.deleteById(customerType.getTypeId());
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }
    @Override
    public CustomerType findById(Long id) {
        return verifyIfExistCustomerType(id);
    }

    @Override
    public CustomerType update(Long id, CustomerTypeDto dto) {
        CustomerType customerTypeBd = verifyIfExistCustomerType(id);
        if (!dto.name().equalsIgnoreCase(customerTypeBd.getName())){
            verifyIfExistCustomerTypeWithName(dto.name());
        }
        BeanUtils.copyProperties(dto,customerTypeBd);
        return customerTypeRepository.save(customerTypeBd);
    }


    private CustomerType verifyIfExistCustomerType(Long id) {
        return customerTypeRepository.findById(id)
                .orElseThrow(()->new NotFoundException("customerType with this id not exist"));
    }
    private void verifyIfExistCustomerTypeWithName(String customerTypeName) {
        var customerTypeBd = customerTypeRepository.findCustomerTypeByName(customerTypeName);
        if (customerTypeBd.isPresent()){
            throw new BusinessException("customerType with this name already exist");
        }
    }
}
