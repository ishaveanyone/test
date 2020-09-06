/**
 * Date: 2020-08-20 19:03
 * Author: xupp
 */

package com.dist.thread;


public class A extends Thread{
    public static Integer  lock=new Integer(1);

    public  void run() {
        System.out.println("A启动");

        synchronized (B.lock){
            System.out.println("a获取b锁");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a尝试获取a锁");

            synchronized (A.lock){
                System.out.println("a已经获取a锁");
            }
            System.out.println("a结束");
        }

    }
}