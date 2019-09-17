package com.dist;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-11 10:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class VolatileTest
{

    static  volatile int a=0;

    static AtomicInteger atomicInteger=new AtomicInteger(1);


    public static void main(String[] args) {
    /*    for(int j =0;j<10000;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                        atomicInteger.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+","+atomicInteger.get());
                }
            }).start();
        }
*/

        System.out.println("你好".length());



    }
}

class DoubleCheckedLocking { // 1
    private static String instance; // 2
    public  String getInstance() { // 3
        if (instance == null) { // 4:第一次检查
            synchronized (DoubleCheckedLocking.class) { // 5:加锁
                if (instance == null) // 6:第二次检查
                    instance = new String("1"); // 7:问题的根源出在这里
            } // 8
        } // 9
        return instance; // 10
    } // 11


    public static void main(String[] args) {
        for (int i=0;i<100000;i++){

            new Thread(() -> {
                System.out.println(new DoubleCheckedLocking().getInstance());
            }).start();
        }
    }
}
