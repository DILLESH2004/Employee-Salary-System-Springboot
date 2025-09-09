package com.example.springjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class SpringJdbcExApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringJdbcExApplication.class, args);
    }
}
