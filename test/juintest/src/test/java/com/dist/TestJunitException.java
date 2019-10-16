package com.dist;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestJunitException extends TestCase {

    //测试一个 方法 明确它 会抛出某一类异常
    public void testException(){
        try{
            int c=1/1;
            fail("throw a math excetion");
        }catch (RuntimeException e){
            assertTrue(true);
        }
    }



}
