package com.example.apicontrolegastos.dto;

import com.example.apicontrolegastos.model.Address;
import com.example.apicontrolegastos.model.Tell;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Builder
public record CustomerDto(
        @NotBlank
        String name,
        @CPF
        String cpf,
        @Email
        String email,
        @Size(min = 1,max = 3)
        String age,
        @NotNull
        Long customerType
) {
}
