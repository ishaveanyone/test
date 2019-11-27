package com.dist.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestContrller {

    @Resource(name = "RedisTemplate")
    private RedisTemplate<String,String> cacheTemplate;

    @GetMapping("/0/{value}")
    public String t0(
            @PathVariable String value
    ){
        ValueOperations<String,String> ops =  cacheTemplate.opsForValue();
        ops.set("a",value);
        return "a";
    }
    @GetMapping("/1")
    public String t2(
    ){
        ListOperations<String,String> ops =  cacheTemplate.opsForList();
        ops.leftPush("b","a");
        ops.leftPush("b","b");
        ops.leftPush("b","c");
        ops.leftPush("b","c");
        return "a";
    }
    @GetMapping("/2")
    public List<String> t3(
    ){
        ListOperations<String,String> ops =  cacheTemplate.opsForList();
        List<String> values= ops.range("b",0,-1);
        System.out.println(values.toString());
        return values;
    }

    @GetMapping("/3")
    public String t4(
    ){
        ListOperations<String,String> ops =  cacheTemplate.opsForList();
        ops.remove("b",1,"a");


        return "a";
    }

}
