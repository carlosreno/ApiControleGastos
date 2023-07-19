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

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerTypeService customerTypeService;

    @Override
    public Customer create(CustomerDto customerDto) {
        verifyIfNotExistCustomerWithCpf(customerDto.cpf());
        verifyIfNotExistCustomerWithEmail(customerDto.email());
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
        Customer customerBd = verifyIfExistCustomerWithId(id);
        CustomerType customerType = getByIdCustomerTypeIfExist(dto.customerType());
        var customerUp = CustomerMapper.fromDtoToEntity(id,customerType,dto);
        verifyIfNewCpfBelongOtherCustomer(customerUp,dto.cpf(),customerBd.getCpf());
        verifyIfNewEmailBelongOtherCustomer(customerUp,dto.email(),customerBd.getEmail());
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
        String cpfBd = customerDb.getCpf();
        verifyIfNewCpfBelongOtherCustomer(customerPatch, cpf, cpfBd);

        String email = dto.email();
        String emailDb = customerDb.getEmail();
        verifyIfNewEmailBelongOtherCustomer(customerPatch, email, emailDb);

        return customerRepository.save(customerPatch);
    }

    private void verifyIfNewEmailBelongOtherCustomer(Customer customerPatch, String email, String emailDb) {
        if (email != null && !email.equals(emailDb)) {
            if (Boolean.TRUE.equals(verifyIfNotExistCustomerWithEmail(email))) {
                customerPatch.setEmail(email);
            }
        }else {
            customerPatch.setEmail(emailDb);
        }
    }

    private void verifyIfNewCpfBelongOtherCustomer(Customer customerPatch, String cpf, String cpfBd) {
        if (cpf != null && !cpf.equals(cpfBd)) {
            if (Boolean.TRUE.equals(verifyIfNotExistCustomerWithCpf(cpf))) {
                customerPatch.setCpf(cpf);
            }
        }else {
            customerPatch.setCpf(cpfBd);
        }
    }

    private Boolean verifyIfNotExistCustomerWithCpf(String cpf){
        if (customerRepository.findCustomerByCpf(cpf).isEmpty()){
            return true;
        }
        throw new BusinessException("Customer with this cpf already exist");
    }
    private Boolean verifyIfNotExistCustomerWithEmail(String email){
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
