package com.dist;

import com.dist.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private ServiceTest ServiceTest;



    Config(){
        System.out.println(1);
    }
}
