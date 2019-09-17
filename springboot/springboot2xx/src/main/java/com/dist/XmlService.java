package com.dist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.admin.SpringApplicationAdminMXBean;
import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-16 15:06
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Service
public class XmlService {

    Logger log= LoggerFactory.getLogger(XmlService.class);
    @Resource
    private TestClass testClass;
    @Autowired
    MailProperties mailProperties;

//    private SpringApplicationAdminMXBeanRegistrar springApplicationAdminMXBeanRegistrar;


    public void getTestClassName() throws InterruptedException {
        RandomValuePropertySource random = new RandomValuePropertySource("myRandom");
        while (true) {
            System.out.println(testClass.getName());
            log.info("random int:{}", random.getProperty("random.int"));

            // 随机生成一个整数，指定上边界，不大于等于1
            log.info("random int(1):{}", random.getProperty("random.int(1)"));
            // 随机生成一个整数，指定上边界，不大于等于5
            log.info("random int(5):{}", random.getProperty("random.int(5)"));

            // 随机生成一个整数，使用区间[0,1),前闭后开=>只能是1
            // 注意区间的表示法：使用()包围,2个字符
            log.info("random int(0,1):{}", random.getProperty("random.int(0,1)"));
            // 随机生成一个整数，使用区间[1,3),前闭后开=>只能是1或者2
            // 注意区间的表示法：使用空格包围,2个字符，前后各一个空格
            log.info("random int(1,3):{}", random.getProperty("random.int 1,3 "));
            // 随机生成一个整数，使用区间[3,4),前闭后开=>只能是3
            // 注意区间的表示法：使用汉字包围,2个字符，前后各一个汉字自负
            log.info("random int(3,4):{}", random.getProperty("random.int底3,4顶"));
            // 随机生成一个整数，使用区间[5,6),前闭后开=>只能是5
            // 注意区间的表示法：使用英文字母包围,2个字符，前后各一个英文字母
            log.info("random int(5,6):{}", random.getProperty("random.intL5,6U"));
            // 随机生成一个整数，使用区间[5,6),前闭后开=>只能是5
            // 注意区间的表示法：使用数字包围,2个字符，前一个数字5，后一个数字6
            log.info("random int(5,6):{}", random.getProperty("random.int55,66"));

            // 随机生成一个长整数
            log.info("random long:{}", random.getProperty("random.long"));
            // 随机生成一个整数，使用区间[100,101),前闭后开=>只能是100
            log.info("random long(100,101):{}", random.getProperty("random.long(100,101)"));

            // 随机生成一个 uuid
            log.info("random uuid:{}", random.getProperty("random.uuid"));

            System.out.println(mailProperties.getFrom());
            Thread.sleep(1000);
        }
    }
}
