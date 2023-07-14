package com.example.apicontrolegastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiControleGastosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiControleGastosApplication.class, args);
    }

}
