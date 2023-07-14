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
@Builder
@Table(name = "customer_type")
public class CustomerType {

    @Id
    @Column(name = "customer_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    private String name;
}
