package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.BusinessException;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CustomerMapper;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.repositories.CustomerRepository;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.service.CustomerTypeService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerTypeService customerTypeService;

    @Override
    public Customer create(CustomerDto customerDto) {
        verifyIfExistCustomerWithCpf(customerDto.cpf());
        verifyIfExistCustomerWithEmail(customerDto.email());
        var customerType = getByIdCustomerTypeIfExist(customerDto.customerType());
        Customer customer = CustomerMapper.fromDtoToEntity(null,customerType,customerDto);
        return customerRepository.save(customer);
    }

    @Override
    public MessageDto delete(Long id) {
        var customer = verifyIfExistCustomerWithId(id);
        customerRepository.deleteById(customer.getCustomerId());
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return verifyIfExistCustomerWithId(id);
    }

    @Override
    public Customer update(Long id, CustomerDto dto) {
        var customerDb = verifyIfExistCustomerWithId(id);
        verifyIfExistCustomerWithEmail(dto.email());
        verifyIfExistCustomerWithCpf(dto.cpf());
        CustomerType customerType = getByIdCustomerTypeIfExist(dto.customerType());
        var customerUp = CustomerMapper.fromDtoToEntity(id,customerType,dto);
        return customerRepository.save(customerUp);
    }

    @Override
    public Customer patch(Long id, CustomerDto dto) {
        Customer customerDb = verifyIfExistCustomerWithId(id);
        var customerPatch = Customer.builder()
                .customerId(customerDb.getCustomerId())
                .name(dto.name()!=null ? dto.name() : customerDb.getName() )
                .age(dto.age()!=null ? dto.age() : customerDb.getAge() )
                .customerType(dto.customerType()==null ? customerDb.getCustomerType() : getByIdCustomerTypeIfExist(dto.customerType()))
                .build();
        String cpf = dto.cpf();
        if (cpf != null && !cpf.equals(customerDb.getCpf())) {
            if (verifyIfExistCustomerWithCpf(cpf)) {
                customerPatch.setCpf(cpf);
            } else {
                throw new BusinessException("customer with this cpf already exist");
            }
        }else {
            customerPatch.setCpf(customerDb.getCpf());
        }

        String email = dto.email();
        if (email != null && !email.equals(customerDb.getEmail())) {
            if (verifyIfExistCustomerWithEmail(email)) {
                customerPatch.setEmail(email);
            } else {
                throw new BusinessException("customer with this email already exist");
            }
        }else {
            customerPatch.setEmail(customerDb.getEmail());
        }

        return customerRepository.save(customerPatch);
    }

    private Boolean verifyIfExistCustomerWithCpf(String cpf){
        if (customerRepository.findCustomerByCpf(cpf).isEmpty()){
            return true;
        }
        throw new BusinessException("Customer with this cpf already exist");
    }
    private Boolean verifyIfExistCustomerWithEmail(String email){
        if (customerRepository.findCustomerByEmail(email).isEmpty()){
            return true;
        }
        throw new BusinessException("Customer with this email already exist");
    }
    private Customer verifyIfExistCustomerWithId(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Customer with this id not exist"));
    }private CustomerType getByIdCustomerTypeIfExist(Long id) {
        return customerTypeService.findById(id);
    }

}
