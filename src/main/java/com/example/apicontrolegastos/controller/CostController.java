package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.CostDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Cost;
import com.example.apicontrolegastos.service.CostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cost")
public class CostController {
    private final CostService costService;
    @PostMapping("/create")
    public ResponseEntity<Cost> create(@Valid @RequestBody CostDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(costService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Cost> put(@PathVariable Long id,@Valid @RequestBody CostDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(costService.update(id,dto));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Cost> patch(@PathVariable Long id,@Valid @RequestBody CostDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(costService.patch(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(costService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<Cost>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(costService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Cost> findAll(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(costService.findById(id));
    }

}

