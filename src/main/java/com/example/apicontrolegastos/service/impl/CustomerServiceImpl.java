package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.BusinessException;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CustomerMapper;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.model.Tell;
import com.example.apicontrolegastos.repositories.AddressRepository;
import com.example.apicontrolegastos.repositories.CustomerRepository;
import com.example.apicontrolegastos.repositories.TellRepository;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.service.CustomerTypeService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerTypeService customerTypeService;
    private final TellRepository tellRepository;
    private final AddressRepository addressRepository;
    @Override
    public Customer create(CustomerDto customerDto) {
        verifyIfExistCustomerWithCpf(customerDto.cpf());
        verifyIfExistCustomerWithEmail(customerDto.email());
        var customerType = getByIdCustomerTypeIfExist(customerDto.customerType());
        Customer customer = CustomerMapper.fromDtoToEntity(customerType,customerDto);
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
        validatedCpf(dto, customerDb);
        validatedEmail(dto, customerDb);

        BeanUtils.copyProperties(dto,customerDb);
        return customerRepository.save(customerDb);
    }

    private boolean validatedCpf(CustomerDto dto, Customer customerDb) {
        if (!dto.cpf().equals(customerDb.getCpf())){
            return verifyIfExistCustomerWithCpf(dto.cpf());
        }
        return true;
    }

    private boolean validatedEmail(CustomerDto dto, Customer customerDb) {
        if (!dto.email().equals(customerDb.getEmail())){
            return verifyIfExistCustomerWithEmail(dto.email());
        }
        return true;
    }

    @Override
    public Customer patch(Long id, CustomerDto dto) {
        Customer customerDb = verifyIfExistCustomerWithId(id);
        var customerPatch = Customer.builder()
                .customerId(customerDb.getCustomerId())
                .name(dto.name()!=null ? dto.name() : customerDb.getName() )
                .age(dto.age()!=null ? dto.age() : customerDb.getAge() )
                .cpf(dto.cpf()!=null ? (validatedCpf(dto, customerDb) ? dto.cpf() : null) : customerDb.getCpf())
                .email(dto.email()!=null ? (validatedEmail(dto, customerDb) ? dto.email() : null) : customerDb.getEmail() )
                .customerType(dto.customerType()==null ? customerDb.getCustomerType() : getByIdCustomerTypeIfExist(dto.customerType()))
                .build();

        List<Address> addressesFinal = new ArrayList<>();
        if (dto.addresses() != null){
            List<Address> addressesSaveAll = dto.addresses().stream()
                    .filter(address -> address.getAddressId() == null)
                    .peek(address -> address.setCustomer(customerDb)).toList();
            addressesFinal.addAll(addressesSaveAll);

            List<Address> addressesUpdateAll = dto.addresses().stream()
                    .filter(address -> address.getAddressId() != null)
                    .map(addressInicial -> {
                        var optAddress = addressRepository.findById(addressInicial.getAddressId());
                        Address addressBd = optAddress.orElseThrow(()-> new NotFoundException("address_id not exist"));
                                return Address.builder()
                                        .addressId(addressBd.getAddressId())
                                        .cep(addressBd.getCep()!=null ? addressInicial.getCep() : addressBd.getCep())
                                        .rua(addressBd.getRua()!=null ? addressInicial.getRua() : addressBd.getRua())
                                        .bairro(addressBd.getBairro()!=null ? addressInicial.getBairro() : addressBd.getBairro())
                                        .numero(addressBd.getNumero()!=null ? addressInicial.getNumero() : addressBd.getNumero())
                                        .cidade(addressBd.getCidade()!=null ? addressInicial.getCidade() : addressBd.getCidade())
                                        .uf(addressBd.getUf()!=null ? addressInicial.getUf() : addressBd.getUf())
                                        .customer(customerDb)
                                        .build();
                    }).toList();
            addressesFinal.addAll(addressesUpdateAll);
            addressRepository.saveAll(addressesFinal);
        }
        List<Tell> tellsFinal = new ArrayList<>();
        if (dto.tells() != null){
            List<Tell> tellsSave = dto.tells().stream()
                    .filter(tell -> tell.getTellId()==null)
                    .peek(tell -> tell.setCustomer(customerDb)).toList();
            tellsFinal.addAll(tellsSave);
            List<Tell> tellsUpdate = dto.tells().stream()
                    .filter(tell -> tell.getTellId()!=null)
                    .map(tell -> {
                        var optTell = tellRepository.findById(tell.getTellId());
                        Tell tellDb = optTell.orElseThrow(() -> new NotFoundException("address_id not exist"));
                        return Tell.builder()
                                .tellId(tellDb.getTellId())
                                .countryCode(tell.getCountryCode() != null ? tell.getCountryCode() : tellDb.getCountryCode())
                                .ddd(tell.getDdd() != null ? tell.getDdd() : tellDb.getDdd())
                                .number(tell.getNumber() != null ? tell.getNumber() : tellDb.getNumber())
                                .customer(tellDb.getCustomer()).build();
                            }
                    ).toList();
            tellsFinal.addAll(tellsUpdate);
            tellRepository.saveAll(tellsFinal);
        }
        return customerRepository.save(customerPatch);
    }

    private Boolean verifyIfExistCustomerWithCpf(String cpf){
        var optCustomer = customerRepository.findCustomerByCpf(cpf);
        if (optCustomer.isPresent()){
            throw new BusinessException("Customer with this cpf already exist");
        }
        return false;
    }
    private Boolean verifyIfExistCustomerWithEmail(String email){
        var optCustomer = customerRepository.findCustomerByEmail(email);
        if (optCustomer.isPresent()){
            throw new BusinessException("Customer with this email already exist");
        }
        return false;
    }
    private Customer verifyIfExistCustomerWithId(Long id){
        var optCustomer = customerRepository.findById(id);
        Customer customer = new Customer();
        optCustomer.ifPresentOrElse(
                customerReturn ->
                BeanUtils.copyProperties(customerReturn,customer),
                ()-> {
                    throw new NotFoundException("Customer with this id not exist");
                }
        );
        return customer;
    }private CustomerType getByIdCustomerTypeIfExist(Long id) {
        return customerTypeService.findById(id);
    }

}
