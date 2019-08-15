package com.dist.annotation;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 16:27
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@MyAnnotation("测试")
public class TestMyAnnotation

{
    public static void main(String[] args) {
        TestMyAnnotation  testMyAnnotation=new TestMyAnnotation();
        Class clazz= testMyAnnotation.getClass();
    }

}
