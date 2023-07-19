package com.example.apicontrolegastos.service.impl;

import com.example.apicontrolegastos.dto.CostDto;
import com.example.apicontrolegastos.dto.MessageDto;
import com.example.apicontrolegastos.exception.NotFoundException;
import com.example.apicontrolegastos.mapper.CostMapper;
import com.example.apicontrolegastos.model.Cost;
import com.example.apicontrolegastos.model.Customer;
import com.example.apicontrolegastos.repositories.CostRepository;
import com.example.apicontrolegastos.service.CostService;
import com.example.apicontrolegastos.service.CustomerService;
import com.example.apicontrolegastos.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {
    private final CostRepository costRepository;
    private final CustomerService customerService;
    @Override
    public Cost create(CostDto costDtp) {
        Customer customer = getCustomerIfExist(costDtp.customerId());
        Cost cost = CostMapper
                .fromDtoToEntity(customer,null,costDtp);
        return costRepository.save(cost);
    }

    @Override
    public MessageDto delete(Long id) {
        Cost cost = verifyIfExistCostWithId(id);
        costRepository.deleteById(cost.getCostId());
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public List<Cost> findAll() {
        return costRepository.findAll();
    }

    @Override
    public Cost findById(Long id) {
        return verifyIfExistCostWithId(id);
    }
    @Override
    public Cost update(Long id, CostDto dto) {
        Cost customerTypeBd = verifyIfExistCostWithId(id);
        BeanUtils.copyProperties(dto,customerTypeBd);
        return costRepository.save(customerTypeBd);
    }
    @Override
    public Cost patch(Long id, CostDto dto) {
        Cost costBd = verifyIfExistCostWithId(id);
        Cost newCost = Cost.builder()
                .costId(costBd.getCostId())
                .customer(dto.customerId() != null ? getCustomerIfExist(dto.customerId()) :costBd.getCustomer())
                .name(dto.name()!=null ? dto.name() : costBd.getName())
                .value(dto.value()!=null ? dto.value() : costBd.getValue())
                .initialDate(dto.initialDate()!=null ? dto.initialDate() : costBd.getInitialDate())
                .finalDate(dto.finalDate()!=null ? dto.finalDate() : costBd.getFinalDate())
                .build();
        return costRepository.save(newCost);
    }

    private Cost verifyIfExistCostWithId(Long id) {
        return costRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Cost with this id not exist"));
    }

    private Customer getCustomerIfExist(Long customerId) {
        return customerService.findById(customerId);
    }

}
