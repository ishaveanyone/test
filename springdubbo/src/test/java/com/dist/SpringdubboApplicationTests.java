package com.dist;


import com.dist.api.IService;
import com.dist.service.ServiceTest;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdubboApplicationTests {

    @Autowired
    IService serviceTest;
    @Test
    public void contextLoads() {
        serviceTest.test();
    }

}
