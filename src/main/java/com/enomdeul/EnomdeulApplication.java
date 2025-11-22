package com.enomdeul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.enomdeul.domain.user",
        "com.enomdeul.domain.skill",
        "com.enomdeul.domain.offer",
        "com.enomdeul.global" // BaseTimeEntity 같은 공통 엔티티
})
@EnableJpaRepositories(basePackages = {
        "com.enomdeul.domain.user",
        "com.enomdeul.domain.skill",
        "com.enomdeul.domain.offer"
})
@ComponentScan(basePackages = {
        "com.enomdeul.domain.user",
        "com.enomdeul.domain.skill",
        "com.enomdeul.domain.offer",
        "com.enomdeul.global"
})
public class EnomdeulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnomdeulApplication.class, args);
    }

}
