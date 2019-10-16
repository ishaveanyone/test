package com.dist;

import java.io.Serializable;

/**
 * 实现一个线程安全的单例
 */
enum SingleObject implements Serializable {
    OBJECT;
    public SingleObject getObject(){
        return OBJECT;
    }
}

public class MyTheardSafeSingle {

    public static void main(String[] args) {
//        SingleObject.OBJECT;
    }

}
