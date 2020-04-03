/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-18 14:37
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist;

import java.util.ArrayList;
import java.util.List;

public class LoopThread {

    public static void main(String[] args) throws InterruptedException {
        List<String> tets=new ArrayList<>();
        while(true){
            Thread.sleep(1000);
            tets.add("aaaaa");
        }
    }
}
