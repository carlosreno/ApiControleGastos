package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.dto.TellDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.model.Tell;
import com.example.apicontrolegastos.repositories.TellRepository;
import com.example.apicontrolegastos.service.TellService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TellServiceImpl implements TellService {
    private final TellRepository tellRepository;

    @Override
    public List<Tell> create(List<TellDto> tells) {
        List<Tell> newTells = tells.stream()
                .map(tellDto -> {
                    Tell tell = new Tell();
                    BeanUtils.copyProperties(tellDto,tell);
                    return tell;
                }).toList();

        return tellRepository.saveAll(newTells);
    }

    @Override
    public MessageDto delete(List<Long> tellsIds) {
        tellRepository.deleteAllById(tellsIds);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public List<Tell> findAll() {
        return tellRepository.findAll();
    }

    @Override
    public Tell findById(Long id) {
        return verifyIfExistTellWithId(id);
    }

    @Override
    public List<Tell> update(List<Tell> tells) {
        verifyIfExistTellWithId(id);
        return null;
    }

    @Override
    public List<Tell> patch(List<Tell> tells) {
        return null;
    }
    private Tell verifyIfExistTellWithId(Long id){
        return tellRepository.findById(id)
                .orElseThrow(()->new NotFoundException("tell with this id not exist"));
    }
}
