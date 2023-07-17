package com.example.apicontrolegastos.dto;

import lombok.Builder;

@Builder
public record TellDto (
        Long tellId,
        Long countryCode,
        Long number,
        Long ddd,
        Long customerId

){}
