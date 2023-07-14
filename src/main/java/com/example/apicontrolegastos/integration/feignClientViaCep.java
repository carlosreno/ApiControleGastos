package com.example.apicontrolegastos.integration;

import com.example.apicontrolegastos.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep",url = "${via-cep.url}")
public interface feignClientViaCep {
    @GetMapping("/{cep}/json")
    AddressDto searchAddress(@PathVariable String cep);
}
