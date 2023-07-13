package com.example.apicontrolegastos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cost")
@Builder
public class Cost {
    @Id
    private Long cost_id;
    private String name;
    private BigDecimal value;
    private LocalDate initial_time;
    private LocalDate final_time;
    @ManyToOne
    private Customer customer;

}
