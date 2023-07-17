package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.CustomerDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<Customer> create(@Valid @RequestBody CustomerDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(dto));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Customer> put(@Valid @PathVariable Long id,@RequestBody CustomerDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id,dto));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Customer> path(@PathVariable Long id, @RequestBody CustomerDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.patch(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Customer> findAll(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

}
