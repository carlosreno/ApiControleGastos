package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.model.Wallet;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletMapper {
    public static Wallet fromDtoToEntity(Long id, Customer customer,WalletDto walletDto){
        return Wallet.builder()
                .walletId(id)
                .name(walletDto.name())
                .value(walletDto.value())
                .customer(customer)
                .build();
    }
}
