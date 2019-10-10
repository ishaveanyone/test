package com.dist.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆上发生 oom 异常
 */
class Test{
    private Integer a;//
    private List list=new ArrayList(10);
}
public class HeapOOM
{

    public static void main(String[] args) {
        for(;;){
            new Test();
        }
    }

}
