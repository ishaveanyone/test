package com.dist.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//查看list源代码



public class ListBak {
    public static void main(String[] args) {
        List<String> testlist=new ArrayList(){{
            add("ddd");add("fff");add("eee");add("ggg");
        }};
//        Arrays.asList(testlist.toArray()).forEach(System.out::println);


//        testlist.forEach(System.out::println);
        List<String> temp=new ArrayList(){{
            add("aaa");add("bbb");add("ccc");
        }};
        //从原数组中指定位置开始进行插入数据
        temp.addAll(3,testlist);

//        temp.forEach(System.out::println);

        temp.removeAll(Arrays.asList("aaa","bbb"));

//        temp.forEach(System.out::println);

        temp.set(1,"nnnn");
        temp.add(1,"yyyy");//插入
        temp.set(1,"ggggggggg");


        System.out.println(temp.listIterator().next());
        System.out.println(temp.listIterator(3).next());

        temp.forEach(System.out::println);

    }
}
