package com.dist.jdksource.lang;


import org.junit.Test;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-21 10:44
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class StringBak {
    String a;

    public static void main(String[] args) {
//        String s="2019-07-24 09:06:45.0";
//        System.out.println(s.substring(0,s.indexOf(" ")+1));
//        char[] chars={'1','3','5','6'};
//        System.out.println(new String(chars,1,2).toString());

        String str1=new StringBuffer("计算机").append("软件").toString();
        String str2=new StringBuffer("ja").append("va").toString();
        String str3="java";
        /**
         * intern 如果 intern 这个在1常量池中存在 ，
         * 所以java 字符串 已经存在 直接返回
         * 那么就会 从常量值直接返回 引用
         * 否则就
         *
         */
        String string4=str3.intern();
        System.out.println(string4.hashCode());
        System.out.println(str3.hashCode());
        System.out.println(str1.intern()==str1);
        System.out.println(str3.intern()==str3);


    }

    @Test
    public void test1(){
        String a="abcdefg";
        System.out.println(a.substring(a.lastIndexOf("b")+1,a.lastIndexOf("e")));
    }

    @Test
    public void test2(){
        Integer a=1;
        System.out.println(a.toString().equals("1"));
    }
}
