/**
 * Date: 2020-09-04 10:31
 * Author: xupp
 */

package com.dist;
//简单测试使用forkjoin框架

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 这是jdk7 提出的分而治之的思想框架
 */
public class ForkJoin {



}

/**
 * fork join 提供了两个任务的实现子类
 */
//是一个 动作没有返回值的动作使用这个类
class MyRecursiveAction extends RecursiveAction{

    public static void main(String[] args) throws InterruptedException {
        MyRecursiveAction myRecursiveAction=new MyRecursiveAction();
        myRecursiveAction.resouces=new ArrayList(){{
            add("1");
            add("2");
            add("3");
            add("4");
        }};
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        forkJoinPool.execute(myRecursiveAction);

        forkJoinPool.awaitTermination(100, TimeUnit.MILLISECONDS);
    }
    //消费一个队列的数据
    List<String> resouces=new ArrayList<>();
    @Override
    protected void compute() {
        if(resouces.size()<=2){
//            System.out.println(1);
            System.out.println("进来了");
            resouces.forEach(o->{
                System.out.println(o);
            });
//            resouces.clear();
        }else{
            MyRecursiveAction lMyRecursiveAction=new MyRecursiveAction();
            MyRecursiveAction rMyRecursiveAction=new MyRecursiveAction();
            lMyRecursiveAction.resouces=resouces.subList(0,resouces.size()/2);
            rMyRecursiveAction.resouces=resouces.subList(resouces.size()/2,resouces.size());
            lMyRecursiveAction.fork();//分支
            rMyRecursiveAction.fork();
        }

    }
}
//如果有返回值叫做任务
//下面模仿一个任务找出一个数组中的所有的偶数
class MyRecursiveTask extends RecursiveTask<Integer> {

    private int [] array;
    int maxLenth=2;
    int count=0;

    public void setArray(int [] array ){
        this.array=array;
    }

    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
        MyRecursiveTask myRecursiveTask=new MyRecursiveTask();
        myRecursiveTask.setArray(array);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        Future<Integer> feature=forkJoinPool.submit(myRecursiveTask);

        try {
//            forkJoinPool.awaitTermination(100, TimeUnit.MILLISECONDS);
            Thread.sleep(5);
            System.out.println(myRecursiveTask.count);
//            System.out.println(feature.get());
            System.out.println(myRecursiveTask.join());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Integer compute() {

        if(array.length<=maxLenth){
            //如果长度小于10 那么直接进行计算
            for (int i : array) {
               if(i%2==0){
                   count++;
               }
            }
        }else {
            MyRecursiveTask leftMyRecursiveTask = new MyRecursiveTask();
            MyRecursiveTask rightMyRecursiveTask = new MyRecursiveTask();
            leftMyRecursiveTask.setArray(Arrays.copyOfRange(array, 0, array.length / 2));
            rightMyRecursiveTask.setArray(Arrays.copyOfRange(array, array.length / 2 , array.length ));
            leftMyRecursiveTask.fork();
            rightMyRecursiveTask.fork();
            int lc = leftMyRecursiveTask.join();
            int rc = rightMyRecursiveTask.join();
            count = lc + rc + count;
        }
        return count;
    }
}