package com.enomdeul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EnomdeulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnomdeulApplication.class, args);
    }

}
