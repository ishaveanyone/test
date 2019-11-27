package com.dist.springbootq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootqApplication.class, args);
    }

}
