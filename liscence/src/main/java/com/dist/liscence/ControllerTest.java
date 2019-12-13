package com.dist.liscence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ControllerTest {

    @GetMapping("/value")
    public String getValue(){
        return "cs";
    }

}
