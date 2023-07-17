package com.example.apicontrolegastos.service;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.TellDto;
import com.example.apicontrolegastos.model.Tell;

import java.util.List;

public interface TellService {
    List<Tell> create(List<TellDto> tells);
    MessageDto delete(Long id);
    List<Tell> findAll();
    Tell findById(Long id);
    List<Tell>  update(List<Tell> tells);
    List<Tell>  patch(List<Tell> tells);
}
