package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.WalletMapper;
import com.example.apicontrolegastos.model.Wallet;
import com.example.apicontrolegastos.repositories.WalletRepository;
import com.example.apicontrolegastos.service.WalletService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    @Override
    public Wallet create(WalletDto walletDto) {
        Wallet wallet = WalletMapper.fromDtoToEntity(walletDto);
        return walletRepository.save(wallet);
    }

    @Override
    public MessageDto delete(Long id) {
        var wallet = verifyIfExisWallet(id);
        walletRepository.deleteById(wallet.getWalletId());
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findById(Long id) {
        return verifyIfExisWallet(id);
    }

    @Override
    public Wallet update(Long id, WalletDto dto) {
        Wallet wallet = verifyIfExisWallet(id);
        BeanUtils.copyProperties(dto,wallet);
        return walletRepository.save(wallet);
    }
    private Wallet verifyIfExisWallet(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Wallet with this id not exist"));
    }
}
