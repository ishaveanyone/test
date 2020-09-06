/**
 * Date: 2020-08-23 14:45
 * Author: xupp
 */

package com.example.aop.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceA {
    @GetMapping("/a/{p}")
    public  String test(@PathVariable int p) throws Exception{
        if(p==1){
            throw  new Exception();
        }
        return "aaa";
    }
}
