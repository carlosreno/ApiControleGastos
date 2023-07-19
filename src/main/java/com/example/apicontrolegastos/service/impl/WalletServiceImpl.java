package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.WalletMapper;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.Wallet;
import com.example.apicontrolegastos.repositories.WalletRepository;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.service.WalletService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final CustomerService customerService;
    private final WalletRepository walletRepository;
    @Override
    public Wallet create(WalletDto walletDto) {
        Customer customer = getCustomerIfExist(walletDto.customerId());
        Wallet wallet = WalletMapper.fromDtoToEntity(null,customer,walletDto);
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
        Wallet walletDb = verifyIfExisWallet(id);
        Customer customer = getCustomerIfExist(dto.customerId());
        Wallet newWallet = WalletMapper.fromDtoToEntity(walletDb.getWalletId(),customer,dto);
        return walletRepository.save(newWallet);
    }@Override
    public Wallet patch(Long id, WalletDto dto) {
        Wallet wallet = verifyIfExisWallet(id);
        Wallet newWallet = Wallet.builder()
                .walletId(wallet.getWalletId())
                .walletId(wallet.getWalletId())
                .customer(dto.customerId()!=null ? getCustomerIfExist(dto.customerId()) : wallet.getCustomer())
                .name(dto.name() != null ? dto.name() : wallet.getName())
                .value(dto.value() != null ? dto.value() : wallet.getValue())
                .build();
        return walletRepository.save(wallet);
    }

    private Wallet verifyIfExisWallet(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Wallet with this id not exist"));
    }
    private Customer getCustomerIfExist(Long customerId) {
        return customerService.findById(customerId);
    }
}
