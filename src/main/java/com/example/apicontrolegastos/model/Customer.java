package com.example.apicontrolegastos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    private Long customer_id;
    private Long age;
    private String name;
    private String cpf;
    @OneToMany
    private List<Cost> cost;
    @OneToOne
    private CustomerType customerType;
    @OneToOne
    private Wallet wallet;
}
