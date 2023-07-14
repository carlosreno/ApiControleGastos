package com.example.apicontrolegastos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerType {
    @Id
    @Column(name = "customer_type")
    private Long typeId;
    private String name;
}
