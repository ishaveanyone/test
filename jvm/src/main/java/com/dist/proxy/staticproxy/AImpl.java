package com.dist.proxy.staticproxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 14:18
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：下面的代理类实现了A接口 所以 静态代理缺点就是 每一个代理里 都要实现对应的接口 这样会导致出现大量的代理类
 */
public class AImpl implements A {
    public void doData() {
        System.out.println("op data!!!");
    }

    public AImpl() {
    }
}
