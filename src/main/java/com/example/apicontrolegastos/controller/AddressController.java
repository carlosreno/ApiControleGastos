package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.AddressDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/create")
    public ResponseEntity<Address> create(@Valid @RequestBody AddressDto addressDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(addressDto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Address> put(@PathVariable Long id,@Valid @RequestBody AddressDto addressDto){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(id,addressDto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Address> path(@PathVariable Long id,@RequestBody AddressDto addressDto){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.patch(id,addressDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<Address>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Address> findAll(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
    }
}
