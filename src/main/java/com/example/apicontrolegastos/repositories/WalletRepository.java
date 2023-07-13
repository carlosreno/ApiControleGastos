package com.example.apicontrolegastos.repositories;

import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
