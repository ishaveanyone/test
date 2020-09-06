/**
 * Date: 2020-08-23 15:22
 * Author: xupp
 */

package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {
    //手工代理
    public static UserService createService(){
        //1.目标类
        final UserService userservice=new UserServiceImpl();
        //2.切面类
        final MyAspect myAspect=new MyAspect();
        //3.代理类：将目标类(切入点)和切面类(通知)结合-->切面
        //Proxy.newProxyInstance
        /*
         * 参数一:loader类加载器，动态代理类，运行时创建，任何类都需要类加载器将其加载至内存。
         * 一般情况下：当前类，class.getClassLoader();
         * 目标类实例:getClass().get...
         * 参数二:interfaces,代理类需要实现的所有接口
         * 方式一:目标类实例.getClass().getInterfaces();注意：只能获得自己的接口，获得不到父元素的接口
         * 方式二:new Class[]{UserService.class}
         * 例如：jdbc驱动
         * 参数三:InvocationHandler 处理类，接口，必须进行实现类，一般采用匿名内部类
         * 提供invoke方法，代理类每一个方法执行时，都将去调用invoke
         * 参数三.1.Object proxy代理对象
         * 参数三.2.Method method 代理对象当前方法的描述对象（反射）
         * 执行方法方法名:method.getName();
         * 执行方法:method.invoke(对象，实际参数)
         * 参数三.3 Object[] args 方法实际参数
         */
        UserService proxyService=(UserService) Proxy.newProxyInstance
                (MyBeanFactory.class.getClassLoader(),
                        userservice.getClass().getInterfaces(), new InvocationHandler(){

                            @Override
                            public Object invoke(Object proxy, Method method,
                                                 Object[] args) throws Throwable {
                                // TODO Auto-generated method stub

                                //前执行
                                myAspect.before();
                                //执行目标类的方法
                                Object obj=method.invoke(userservice, args);

                                //后执行
                                myAspect.after();

                                return null;
                            }
                        });
        return proxyService;
    }
}
