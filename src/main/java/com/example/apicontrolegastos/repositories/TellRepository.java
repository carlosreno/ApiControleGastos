package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.Cost;
import com.example.apicontrolegastos.model.Tell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellRepository extends JpaRepository<Tell,Long> {
}
