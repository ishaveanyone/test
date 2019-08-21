package com.dist.jmx;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-21 9:39
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class Test implements TestMBean{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
