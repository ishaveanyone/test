/**
 * Date: 2020-08-19 16:34
 * Author: xupp
 */

package com.dist.thread;

import java.util.HashMap;

public class DeadLock {
    public static void main(String[] args) {
        A a=new A();

        B b=new B();

        Object at=A.lock;
        Object bt=B.lock;
        a.start();
        b.start();
    }
}
