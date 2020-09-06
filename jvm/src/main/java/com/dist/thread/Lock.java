/**
 * Date: 2020-08-29 10:58
 * Author: xupp
 */

package com.dist.thread;

import com.dist.proxy.cglibproxy.P;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
   static   ReentrantLock reentrantLock=new ReentrantLock();

    public static void main(String[] args) {
        new RInLock().start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new RInLock2().start();

    }
}

class RInLock extends Thread{
    @Override
    public void run(){
        while(true) {
            Lock.reentrantLock.lock();
            Lock.reentrantLock.lock();
            //说明lock 中的 lock
            // 和unlock必须是一一对应的
            System.out.println(1);
            Lock.reentrantLock.unlock();


        }

    }
}
class RInLock2 extends Thread{
    @Override
    public void run(){
        while (true) {
            Lock.reentrantLock.lock();
            System.out.println(2);
            Lock.reentrantLock.unlock();
        }
    }
}