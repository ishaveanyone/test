/**
 * Date: 2020-08-23 15:22
 * Author: xupp
 */

package com.example.aop;

public class MyAspect {
    public void before(){
        System.out.println("鸡头");
    }

    public void after(){
        System.out.println("牛后");
    }
}
