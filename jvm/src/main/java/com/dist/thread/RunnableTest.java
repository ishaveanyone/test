/**
 * Date: 2020-07-23 15:03
 * Author: xupp
 */

package com.dist.thread;

public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        new Thread(new RunnableTest()).start();
    }
}
