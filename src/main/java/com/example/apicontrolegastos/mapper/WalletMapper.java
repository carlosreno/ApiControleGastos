package com.example.apicontrolegastos.mapper;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.model.Wallet;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletMapper {
    public static Wallet fromDtoToEntity(WalletDto walletDto){
        return Wallet.builder()
                .name(walletDto.name())
                .value(walletDto.value())
                .build();
    }
}
