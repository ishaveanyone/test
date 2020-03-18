package com.dist;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

public class JuitTest2 extends TestCase {

    public JuitTest2(String method){
        super(method);
    }
    @Test
    public void testEqu1(){
        System.out.println(11);
    }

    @Test
    public void testEqu2(){
        System.out.println(22);

    }

    @Test
    public void testEqu3(){
        System.out.println(33);
    }



}
