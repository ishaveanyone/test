/**
 * Date: 2020-07-23 15:05
 * Author: xupp
 */

package com.dist.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable  {

    @Override
    public Object call() throws Exception {
        System.out.println(1);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=new FutureTask(new CallableTest());
        new Thread(futureTask).start();
        futureTask.get();//阻塞主线程
    }
}
