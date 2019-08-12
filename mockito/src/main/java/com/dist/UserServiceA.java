package com.dist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceA {

    @Autowired
    private RoleServiceA roleServiceA;

    public User findOneUser(){
        return new User("user",roleServiceA.findOneRole());
    }

}
