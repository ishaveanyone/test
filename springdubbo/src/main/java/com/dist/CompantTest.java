package com.dist;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.dist.api.IService;
import com.dist.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompantTest {
    @Autowired
    private ServiceTest iService;

    public  void p(){
        iService.test();
    }
}
