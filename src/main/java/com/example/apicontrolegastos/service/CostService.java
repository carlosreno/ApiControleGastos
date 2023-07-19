package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.CostDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.model.Cost;

import java.util.List;

public interface CostService {
    Cost create(CostDto costDto);
    MessageDto delete(Long id);
    List<Cost> findAll();

    Cost findById(Long id);

    Cost update(Long id, CostDto costDto);
    Cost patch(Long id, CostDto costDto);
}
