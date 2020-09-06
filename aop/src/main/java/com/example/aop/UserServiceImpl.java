/**
 * Date: 2020-08-23 15:21
 * Author: xupp
 */

package com.example.aop;

public class UserServiceImpl implements UserService{

    @Override
    public void addUser() {
        // TODO Auto-generated method stub
        System.out.println("proxy addUser");
    }

    @Override
    public void updateUser() {
        // TODO Auto-generated method stub
        System.out.println("proxy updateUser");
    }

    @Override
    public void deleteUser() {
        // TODO Auto-generated method stub
        System.out.println("proxy deleteUser");
    }

}
