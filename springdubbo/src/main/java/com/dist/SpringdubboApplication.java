package com.dist;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dist.api.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring-dubbo-provider.xml"})
public class SpringdubboApplication {
    @Autowired
    private static CompantTest compantTest;
    @Reference
    static IService iService;
    public static void main(String[] args) {
        SpringApplication.run(SpringdubboApplication.class, args);
        compantTest.p();


    }

}
