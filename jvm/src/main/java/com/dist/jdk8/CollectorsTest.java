package com.dist.jdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-12 14:55
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class CollectorsTest {

    public static void test0() {
        List<String> list1=new ArrayList<>();
        list1.add("a");
        list1.add("1");

        List<String> list2=new ArrayList<>();
        list2.add("a");
        list2.add("2");


        List<String> list3=new ArrayList<>();
        list3.add("b");
        list3.add("1");

        List<String> list4=new ArrayList<>();
        list4.add("b");
        list4.add("2");

        List<List<String>> lists=new ArrayList(){{

            add(list1);add(list3);add(list2);add(list4);
        }};

         Map<String,List<List<String>>> data= lists.stream().
                 collect(Collectors.groupingBy(o ->((List<String>)o).get(0)));
        data.forEach((s, lists1) -> {
            System.out.println(s);
            System.out.println(lists1.toString());
        });
//        lists.forEach(strings -> strings.forEach(System.out::print));
    }


    public static void test1() {

        List<String> list1 = Arrays.asList("", "1", "2", "", "1", "3", "", "");
        List<String> list2 = Arrays.asList("", "1", "2", "", "1", "3", "", "");
        List<String> list3 = Arrays.asList("", "1", "2", "", "1", "3", "", "");
        List<List<String>> datas =Arrays.asList(list1,list2,list3);


        datas= datas.stream().map(strings -> strings.stream().map(s ->"".equals(s)?"-":s ).collect(Collectors.toList())).collect(Collectors.toList());

        datas.forEach(strings -> {
            strings.forEach(System.out::println);
        });
    }

   static void test2(){
       Integer o1= new Integer(1);
       Integer o2= new Integer(2);
       Integer o3= new Integer(3);
      List<Integer> os= Arrays.asList(o1,o2,o3);
       List<Integer> arrList = new ArrayList(os);
     Optional<Integer> op= arrList.stream().filter(o->o==1).findFirst();

     if(op.isPresent()){
         Integer o4=op.get();
         arrList.remove(o4);
         System.out.println(arrList.size());
     }



   }

    public static void main(String[] args) {

        System.out.println("a".contains(""));
        test2();
    }
}
