package com.dist.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//阅读ArrayList 源码
public class ArrayListBak {


    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList();
        list.add(null);//可以保存空
        System.out.println(list.size());//1

        List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
        }});//这样做可以获取到一个线程安全的arraylist
        //在便利上面的；list的时候 我们需要进行下面这样操纵 否则会出现 一些问题
        int in=0;
         synchronized (list2) {
             Iterator<Integer> i = list2.iterator(); // Must be in synchronized block
             while (i.hasNext())
              in=i.next();
         }
        System.out.println(in);
    }

}
