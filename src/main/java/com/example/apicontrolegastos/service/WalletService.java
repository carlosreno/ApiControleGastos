package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.model.Wallet;

import java.util.List;

public interface WalletService {
    Wallet create(WalletDto walletDto);
    MessageDto delete(Long id);
    List<Wallet> findAll();

    Wallet findById(Long id);

    Wallet update(Long id, WalletDto dto);
    Wallet patch(Long id, WalletDto dto);
}
