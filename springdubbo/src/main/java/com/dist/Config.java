package com.dist;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dist.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Reference
    private ServiceTest ServiceTest;



    Config(){
        System.out.println(1);
    }
}
