package com.dist.proxy.dynamicproxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 14:57
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：缺点是 必须有一个接口 用于生产代理类
 */
public class BImpl implements B{
    @Override
    public void doData() {
        System.out.println("start op data");
    }
}
