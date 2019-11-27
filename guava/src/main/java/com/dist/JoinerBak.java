package com.dist;

import com.google.common.base.Joiner;

import java.util.*;

public class JoinerBak {
    public static void main(String[] args) {
        Map<String,Integer> map=new LinkedHashMap<>();

        map.put("a",1);
        map.put("b",2);
        map.put("c",3);

       Collection<Integer> values=map.values();
       String test= Joiner.on(",").join(values);

        System.out.println(test);

    }
}
