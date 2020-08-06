/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-25 14:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap(){{
           put(new Integer(1),"aaa");
           put(new Integer(2),"bb");
        }};

        System.out.println(map.get(new Integer(1)));
     }
}
