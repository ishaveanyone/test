package com.dist;

import org.springframework.web.util.HtmlUtils;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-12 11:30
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class HtmlUtilTest
{

    public static void main(String[] args) {
        System.out.println(HtmlUtils.htmlEscape("i woshi1 你好 <"));
        System.out.println(HtmlUtils.htmlUnescape("i woshi1 你好 &lt;"));
    }
}
