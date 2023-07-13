package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.Cost;
import com.example.apicontrolegastos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
