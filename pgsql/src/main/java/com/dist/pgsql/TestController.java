package com.dist.pgsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired  TResp tResp;
    @Autowired  T2Resp t2Resp;
    @Autowired  T3Resp t3Resp;
    @Autowired  T1Resp t1Resp;
    @Autowired  T5Resp t5Resp;
    @GetMapping("/t")
    public T gett(){
        System.out.println(1);
        return  tResp.findById(1).get();
    }

    @GetMapping("/t2")
    public T2 gett2(){
        System.out.println(1);
        return  t2Resp.findById(1).get();
    }

    @GetMapping("/t3")
    public T3 gett3(){
        System.out.println(1);
        return  t3Resp.findById(1).get();
    }

    @GetMapping("/t1")
    public T1 gett1(){
        System.out.println(1);
        return  t1Resp.findById(1).get();
    }

    @GetMapping("/t5")
    public T5 gett5(){
        System.out.println(1);
        return  t5Resp.findById(1).get();
    }
}
