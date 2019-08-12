package com.dist;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class ExecutorsTest {

    public static void main(String[] args) {
//        singleThreadExecutorTest();
//        fixedThreadPoolTest();
//        cachedThreadPool();
        scheduledThreadPool();
    }

/*
创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
如果这个唯一的线程因为异常结束，
那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
* */
    public static void singleThreadExecutorTest() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        IntStream.range(0, 5).forEach(i -> executor.execute(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("finished: " + threadName);
        }));

        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }

/*
* 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
* 线程池的大小一旦达到最大值就会保持不变，在提交新任务，任务将会进入等待队列中等待。
* 如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
* */
    public static void fixedThreadPoolTest() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        IntStream.range(0, 6).forEach(i -> executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                String threadName = Thread.currentThread().getName();
                System.out.println("finished: " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        try {
            // close pool
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }


/*
* 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，

那么就会回收部分空闲（60秒处于等待任务到来）的线程，当任务数增加时，
此线程池又可以智能的添加新线程来处理任务。此线程池的最大值是Integer的最大值(2^31-1)。
* */
    public static void cachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.range(0, 6).forEach(i -> executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                String threadName = Thread.currentThread().getName();
                System.out.println("finished: " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        try {
            // close pool
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }

//    创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
    public static void scheduledThreadPool() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() ->
                        System.out.println(LocalDateTime.now().getSecond())
                , 1000, 3000, TimeUnit.MILLISECONDS);

        try {
            // close pool
            Thread.sleep(20000);
            executor.shutdown();
//            当使用awaitTermination时，主线程会处于一种等待的状态，等待线程池中所有的线程都运行完毕后才继续运行。
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
// 若关闭后所有任务都已完成，则返回true。注意除非首先调用shutdown或shutdownNow，
// 否则isTerminated永不为true。返回：若关闭后所有任务都已完成，则返回true。
            if(executor.isTerminated()) {
                executor.shutdownNow();
            }
        }
    }


}

