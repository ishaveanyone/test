package com.dist;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-22 9:58
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class LockTest {

   static ReentrantLock reentrantLock=new ReentrantLock();
    public static void out1(){
        reentrantLock.lock();
    }
    public static void out2(){
        reentrantLock.lock();
    }

    public static void main(String[] args) {
        out1();//不会抢占锁 这是一个共享锁
        out2();
    }
}
