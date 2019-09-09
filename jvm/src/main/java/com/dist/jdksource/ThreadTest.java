package com.dist.jdksource;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-30 19:43
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class ThreadTest {
    static  int i;
    static  int j;
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();
        for (int k = 0; k < 1000000; k++) {
            i=5;
            j=100;
            Map<String, Integer> map = new HashMap();
            CountDownLatch latch = new CountDownLatch(2);
            Thread thread1 = new Thread(() -> {
                i++;
                map.put("a", j);
                latch.countDown();
            });
            Thread thread2 = new Thread(() -> {
                j++;
                map.put("b", i);
                latch.countDown();
            });
            thread1.start();
            thread2.start();
            latch.await();
            set.add(map);
            set.iterator().next();
            System.out.println(set);
        }
    }

}