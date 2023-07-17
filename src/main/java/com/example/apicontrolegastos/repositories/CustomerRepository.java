package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByCpf(String cpf);
    Optional<Customer> findCustomerByEmail(String email);
}
