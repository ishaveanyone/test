package com.dist.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dist.api.IService;
import org.springframework.stereotype.Component;

@Service
public class ServiceTest implements IService {

    public void test(){
        System.out.println(1);
    }
}
