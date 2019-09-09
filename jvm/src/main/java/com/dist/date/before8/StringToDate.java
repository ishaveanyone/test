package com.dist.date.before8;

import org.joda.time.DateTime;

import java.util.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-30 9:39
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：字符串转日期
 */
public class StringToDate {

    public static void main(String[] args) {
        LinkedHashMap<String,Integer> map=new LinkedHashMap<>();
        DateTime dateTime1=new DateTime("2017-09-01");
        DateTime dateTime2=new DateTime("2017-07-01");
        map.put("2017-09-01",1);
        map.put("2017-07-01",2);


        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                Long sjc1= (new DateTime(o1.getKey())).getMillis();
                Long sjc2= (new DateTime(o2.getKey())).getMillis();
                return sjc1.compareTo(sjc2);
            }
        });
        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            map2.put(entry.getKey(), entry.getValue());
        }



        System.out.println(map2);
    }

}
