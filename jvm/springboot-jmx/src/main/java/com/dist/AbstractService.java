package com.dist;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-23 13:12
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public abstract class AbstractService {
    @Autowired
    protected Service1 service1;

    public void test(){
        service1.out();
    }

}
