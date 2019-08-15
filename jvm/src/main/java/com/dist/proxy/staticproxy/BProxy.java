package com.dist.proxy.staticproxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 14:19
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：代理类 现在有一个需求就是 需要在不改动 a的 业务逻辑情况下 在操作数据前后执行代码
 */
public class BProxy implements A{
    private A a;
    public BProxy(AImpl a) {

        this.a=a;
    }


    public void doData() {
        System.out.println("before op data");
        a.doData();
        System.out.println("after op data");
    }
}
