package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends JpaRepository<Cost,Long> {
}
