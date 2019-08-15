package com.dist.proxy.cglibproxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 15:37
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class App {

    public static void main(String[] args) {
        UserDao userDao=new UserDao();
        ProxyFactory proxyFactory=new ProxyFactory(userDao);
        UserDao proxy = (UserDao) proxyFactory.getProxyInstance();
        proxy.save();
    }
}
