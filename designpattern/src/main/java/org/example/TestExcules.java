/**
 * Date: 2020-08-21 14:31
 * Author: xupp
 */

package org.example;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class TestExcules {

    public static void main(String[] args) {
//        ScheduledExecutorService executorService =
//                Executors.newScheduledThreadPool(2);
////        for (int i = 0; i < 15; i++) {
//        executorService.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(Thread.currentThread().getState());
//
//                System.out.println("_---------------");
//            }
//        },2,5, TimeUnit.SECONDS);
////        }
//
//
        BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue(6);
        workQueue.add(new Runnable() {
            public void run() {
                System.out.println(1);
            }
        });

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                5// corePoolSize,
        ,1,//int maximumPoolSize,
        2,//long keepAliveTime,
                TimeUnit.SECONDS,// TimeUnit unit,
         workQueue);
        ReentrantLock reentrantLock=new ReentrantLock(true);
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
