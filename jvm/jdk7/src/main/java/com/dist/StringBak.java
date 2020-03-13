package com.dist;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

//        String a="http://127.0.0.1:8080/ddd";
//       int i1= a.indexOf("//")+2;
//       int i2= a.indexOf(":",i1);
//        System.out.println(i1+"-"+i2);
//        System.out.println();
//        System.out.println(a.replace(a.substring(i1,i2),"192.168.1.94"));
//        System.out.println(a);
        double d = 114.145;
         BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(d);


    }
}
