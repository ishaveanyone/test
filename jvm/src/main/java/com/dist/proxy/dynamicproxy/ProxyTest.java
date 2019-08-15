package com.dist.proxy.dynamicproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 14:48
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        //获取代理类的class对象
        Class bClass=Proxy.getProxyClass(B.class.getClassLoader(),B.class);
        Constructor constructor=bClass.getConstructor(InvocationHandler.class);
        B b=(B)constructor.newInstance(new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                BImpl b1=new BImpl();
                System.out.println("start log");
                method.invoke(b1,args);
                System.out.println("end log");
                return null;
            }
        });
        b.doData();
        System.out.println(1);

    }
}
