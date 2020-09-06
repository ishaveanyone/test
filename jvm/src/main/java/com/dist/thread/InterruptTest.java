/**
 * Date: 2020-08-29 8:39
 * Author: xupp
 */

package com.dist.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class  Theard1 extends Thread{
    @Override
    public void run() {

        try {
            while (true){
                System.out.println(1);
            }
        } catch (Error err) {
            err.printStackTrace();
        }
    }
}

class  Theard2 extends Thread{
    @Override
    public void run() {
        while (!InterruptTest.stop){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
public class InterruptTest {
    public static volatile boolean stop=false;
    public static void main(String[] args) throws InterruptedException {
        Theard2 theard2=new Theard2();
        theard2.setName("2");
        theard2.start();
        TimeUnit.SECONDS.sleep(1);
        stop=true;
    }
}


class Tt{

    private static final int[] array = new int[80000];
    private static final Thread t = new Thread() {
        public void run() {
            try {
              while (true){
                  System.out.println(1);
              }
            } catch (Error err) {
                err.printStackTrace();
            }
            System.out.println("in thread t");
        }
    };

    static {
        Random random = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(i + 1);
        }
    }

    private static int sort(int[] array) {
        for (int i = 0; i < array.length-1; i++){
            for(int j = 0 ;j < array.length - i - 1; j++){
                if(array[j] < array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array[0];
    }

    public static void main(String[] args) throws Exception {
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("go to stop thread t");
        t.stop();
        System.out.println("finish main");
    }
}


