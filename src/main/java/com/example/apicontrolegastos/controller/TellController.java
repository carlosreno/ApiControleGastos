package com.example.apicontrolegastos.controller;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.TellDto;
import com.example.apicontrolegastos.model.Tell;
import com.example.apicontrolegastos.service.TellService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tell")
@RequiredArgsConstructor
public class TellController {
    private final TellService tellService;
    @PostMapping("/create")
    public ResponseEntity<List<Tell>> create(@Valid @RequestBody List<TellDto> tell){
        return ResponseEntity.status(HttpStatus.CREATED).body(tellService.create(tell));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<List<Tell>> put(@Valid @PathVariable Long id, @RequestBody List<Tell> tell){
        return ResponseEntity.status(HttpStatus.OK).body(tellService.update(tell));
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<List<Tell>> path(@PathVariable Long id, @RequestBody List<Tell> tell){
        return ResponseEntity.status(HttpStatus.OK).body(tellService.patch(tell));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tellService.delete(id));
    }
    @GetMapping("/list-all")
    public ResponseEntity<List<Tell>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(tellService.findAll());
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Tell> findAll(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tellService.findById(id));
    }
}
