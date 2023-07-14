package com.example.apicontrolegastos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private Long numero;
    @ManyToOne
    private Customer customer;
}
