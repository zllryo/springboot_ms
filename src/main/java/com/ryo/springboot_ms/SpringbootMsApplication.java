package com.ryo.springboot_ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ryo.springboot_ms.dao")
public class SpringbootMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMsApplication.class, args);
    }
}
