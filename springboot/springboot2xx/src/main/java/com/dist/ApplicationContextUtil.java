package com.dist;

import org.springframework.context.ApplicationContext;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-16 15:49
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class ApplicationContextUtil {
   static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext applicationContext){
        applicationContext=applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
