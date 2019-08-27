package com.dist;

import org.springframework.stereotype.Service;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-23 13:13
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Service
public class Service1 {
    Service1(){
        System.out.println("init--------------------");
    }
    public void out (){
        System.out.println(1111);
    }
}
