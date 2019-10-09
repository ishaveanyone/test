package com.dist.collection;

import java.util.ArrayList;
import java.util.HashMap;
//jdk8 提供了 更多的支持 lambadle语法的操作
public class HashMapBak {

    public static void main(String[] args) {
        HashMap<String,String> hashMap=new HashMap(){{

            put("1","a");//这个是有值的 只不过 返回是先前的value 如果 容器没有已经存在的 key 那么就返回null
            put("2","b");
            put("3","c");
            put("5","c");
//            put("4","m");
        }};
        hashMap.putIfAbsent("1","d");//存在的情况下就不进行替换

        hashMap.computeIfAbsent("4", key->key+"a");//对应key 不存在的情况的 时候 ，
        // 就将 返回值 存入 并且是将 key 作为计算参数

        hashMap.computeIfPresent("5", (key,value)->value+"a");//用来进行数据替换操作
        System.out.println(hashMap.getOrDefault("1","d"));
        System.out.println(hashMap.getOrDefault("5","6"));
        System.out.println(hashMap.getOrDefault("4","6"));
        hashMap.compute(null,(key,key2)->"a");
        hashMap.compute("7",(key,key2)->"a");
        System.out.println(hashMap.size());
        System.out.println(hashMap.get("7"));
        String msg="ff";

        hashMap.compute("1",(k,v)->
                v.concat(msg)
        );

        hashMap.compute("1",(k,v)->null);//如果返回值是null 如果存在就一处否则不添加
        System.out.println(hashMap.size());
        System.out.println(hashMap.get("1"));

        hashMap.merge("2","test",(m,v)->m+1+v+1);
        System.out.println(hashMap.get("2"));
        hashMap.replaceAll((k,v)->
            k+v
        );
        hashMap.forEach((k,v)->{
            System.out.println(k+"---"+v);
        });
    }
}
