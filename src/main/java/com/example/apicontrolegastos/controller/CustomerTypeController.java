package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.CustomerTypeDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.CustomerType;
import com.example.apicontrolegastos.service.CustomerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-type")
@RequiredArgsConstructor
public class CustomerTypeController {
    private final CustomerTypeService customerTypeService;
    @PostMapping("/create")
    public ResponseEntity<CustomerType> create(@RequestBody CustomerTypeDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerTypeService.create(dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerTypeService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<CustomerType>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerTypeService.findAll());
    }

}
