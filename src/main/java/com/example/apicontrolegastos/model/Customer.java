package com.example.apicontrolegastos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String cpf;
    private String email;
    private String age;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Tell> tells;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_type_id")
    private CustomerType customerType;

}
