package com.example.apicontrolegastos.controller;

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
    public ResponseEntity<Address> create(@Valid @RequestBody Address address){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(address));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Address> put(@Valid  @RequestBody Address address){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(address));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Address> path(@RequestBody Address address){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.patch(address));
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
