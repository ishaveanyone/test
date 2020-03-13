package com.dist.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//有序的map 其实 操作和Hashmap 并没有什么区别
public class LinkHashMapBak {

    public static void main(String[] args) {
        Map<String,String> linkedHashMap=new HashMap(){{
            put("中文测试","aa");
        }};

        System.out.println(linkedHashMap.get("中文测试"));
    }
}
