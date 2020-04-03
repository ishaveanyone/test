package com.dist.pgsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired  TestRepo testRepo;


    @GetMapping("/test")
    public TestEntity getttest(){
        System.out.println(1);
        return testRepo.findAll().iterator().next();
    }


}

