package com.dist;

import org.springframework.stereotype.Service;


@Service
public class RoleServiceA {
    public  Role findOneRole(){
        return new Role("role");
    }
}
