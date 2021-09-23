package com.example.wv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.wv.mapper")
public class WvApplication {

    public static void main(String[] args) {
        SpringApplication.run(WvApplication.class, args);
    }

}
