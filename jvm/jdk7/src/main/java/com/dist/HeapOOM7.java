package com.dist;


import java.util.ArrayList;
import java.util.List;

/**
 * 堆上空间当无法分配的时候 会出现oom 指定最大空间和最小空间 并不会 在到达的啥时候 抛出oom
 */
public class HeapOOM7 {


    public static void main(String[] args) {

        List<String> tetst=new ArrayList<>();
        System.out.println(7827346/1024/1024);//15M
        for(;;){
            tetst.add("f");
        }

    }
}
