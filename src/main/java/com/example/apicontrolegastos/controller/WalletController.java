package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.WalletDto;
import com.example.apicontrolegastos.model.Wallet;
import com.example.apicontrolegastos.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @PostMapping("/create")
    public ResponseEntity<Wallet> create(@Valid @RequestBody WalletDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Wallet> put(@PathVariable Long id,@Valid @RequestBody WalletDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(walletService.update(id,dto));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Wallet> patch(@PathVariable Long id,@RequestBody WalletDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(walletService.patch(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<Wallet>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Wallet> findAll(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.findById(id));
    }

}
