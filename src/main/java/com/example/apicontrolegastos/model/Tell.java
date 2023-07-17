package com.example.apicontrolegastos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tell")
public class Tell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tell_id")
    private Long tellId;
    private Long countryCode;
    private Long number;
    private Long ddd;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

