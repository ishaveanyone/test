/**
 * Date: 2020-08-20 19:03
 * Author: xupp
 */

package com.dist.thread;


public class B extends Thread{
    public static Integer  lock=1;


    @Override
    public void run() {
        System.out.println("B启动");
        synchronized (A.lock){
            System.out.println("b尝试获取a锁");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b尝试获取b锁");

            synchronized (B.lock){
                System.out.println("b已经获取b锁");
            }
            System.out.println("b结束");
        }
    }
}