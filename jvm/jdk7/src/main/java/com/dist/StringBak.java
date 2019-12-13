package com.dist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringBak {

    public static void main(String[] args) {
//        System.out.println(new Integer(1).toString().equals("1"));
/*
        String[]  strs= "a,null".split(",");
        System.out.println(null==null);
        strs[0]=null;
        for (String str : strs) {
            System.out.println(str);
        }*/

//        List<Integer> list= Arrays.asList(1,2,3);
//        System.out.println(list.contains(new Integer(1)));

        String a="/a/b/c/class/";
        System.out.println(a.substring(a.indexOf('/')+1));
    }
}
