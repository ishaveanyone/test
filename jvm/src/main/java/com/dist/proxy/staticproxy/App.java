package com.dist.proxy.staticproxy;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 14:23
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class App {

    public static void main(String[] args) {

        BProxy bProxy=new BProxy(new AImpl());
        bProxy.doData();

    }
}
