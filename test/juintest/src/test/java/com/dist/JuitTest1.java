package com.dist;

import junit.extensions.TestSetup;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;


public class JuitTest1 extends TestCase {
    //每一个测试方法开始之前就会进行执行
    @Override
    protected void setUp() throws Exception {
        System.out.println("init");
    }

    public JuitTest1(String method){
        super(method);
    }
    @Test
    public void testEqu1(){
        System.out.println(1);
    }

    @Test
    public void testEqu2(){

        System.out.println(2);
    }

    @Test
    public void testEqu3(){
        System.out.println(3);
    }
//每一个测试方法执行之后就会执行
    @Override
    protected void tearDown() throws Exception {
        System.out.println("destory");
    }

    //使用这个 可以指定具体运行哪几个方法 ，否则会运行所有的测试方法
    public static junit.framework.Test suite(){
        TestSuite suite=new TestSuite();
        suite.addTest(new JuitTest1("testEqu1"));
        suite.addTest(new JuitTest1("testEqu2"));
        //对于suite 层面进行开始和结束的工作内容
        TestSetup wapper=new TestSetup(suite){

            @Override
            protected void setUp() throws Exception {
                System.out.println("init--");
            }

            @Override
            protected void tearDown() throws Exception {
                System.out.println("destory--");
            }

        };

        return wapper;
    }

}
