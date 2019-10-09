package com.dist.collection;

import com.sun.media.sound.SoftTuning;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

//map 就是在 有序的 基础 上  还保证了 key有序
public class TreeMapBak {

    public static void main(String[] args) {

        TreeMap treeMap=new TreeMap(Comparator.reverseOrder());
        for(Integer i=10000;i>1;i--){
            treeMap.put(i,i);
        }


        treeMap.forEach((k,v)-> System.out.println(k));

        System.out.println(treeMap.comparator());

        Collections.synchronizedSortedMap(treeMap);


//        System.out.println(treeMap

//        treeMap.put("string","ok");//java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
    }

}
