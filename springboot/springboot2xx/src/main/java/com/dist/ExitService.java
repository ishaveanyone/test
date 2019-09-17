package com.dist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-16 15:44
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Service
public class ExitService {
    @Autowired
    private ExitCodeGenerator exitCode;

    public void exit(){
        //退出应用之后 不能就 变成 null 了
        ApplicationContext applicationContext=ApplicationContextUtil.getApplicationContext();
        SpringApplication.exit(applicationContext,exitCode);
    }
}
