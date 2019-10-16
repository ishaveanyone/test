package com.dist;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
//组合测试类
public class CompositeTest extends TestCase {
    public static Test suite(){
        TestSuite testSuite=new TestSuite();
        testSuite.addTest(JuitTest1.suite());
        testSuite.addTestSuite(JuitTest2.class);
        return testSuite;
    }
}
