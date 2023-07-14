package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType,Long> {
    Optional<CustomerType> findCustomerTypeByName(String name);
}
