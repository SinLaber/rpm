package com.fish.rpm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fish.rpm.dao.measure")
public class RpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpmApplication.class, args);
    }

}
