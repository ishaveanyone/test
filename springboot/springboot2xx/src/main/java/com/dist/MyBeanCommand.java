package com.dist;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-16 16:02
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：启动之后运行一些代码
 */
@Component
@Order(value = 0)
public class MyBeanCommand implements CommandLineRunner,Ordered {
    @Override
    //下面的参数是 args
    public void run(String... strings) throws Exception {
        System.out.println("ok hhhh");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
@Component
@Order(value = 1)
class MyBeanCommand2 implements ApplicationRunner,Ordered {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("3333333333");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

