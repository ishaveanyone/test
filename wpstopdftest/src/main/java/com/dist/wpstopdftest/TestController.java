/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-01-07 10:30
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist.wpstopdftest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/preview")

    public String preview(){
        WpsToPdf.convert2PDF("D:/prj_lshw.xls","D:/prj_lshw.pdf");
        return "success";
    }
}
