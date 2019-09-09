package com.dist;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-02 14:15
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：由于某些bean 创建过于复杂 所以可以交给第三方 builder进行创建
 */

import sun.security.jca.GetInstance;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 有可能电脑里面 有 cpu 有 硬盘 有 屏幕 但是也有可能 什么 也没有
 *
 * 所以 客户端 实例化这个 类的 时候 情况比较多 如果 通过 重叠构造器实现的话 就不方便阅读
 * 使用 builder 模式 可以将类的实例 化 和 过程 解耦 并且 提高了 代码 的 可读性
 */
class Computer{

    int cpu;

    int disk;

    int sceem;

    @Override
    public String toString() {
        return "cpu:"+cpu+"-"+"disk:"+disk+"-"+"sceem:"+sceem;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getSceem() {
        return sceem;
    }

    public void setSceem(int sceem) {
        this.sceem = sceem;
    }
}
interface Bulider<T>{
    Bulider cpu(int cpu);
    Bulider disk(int disk);
    Bulider sceem(int sceem);
    T build();
}
class DefaultComputerBuilder implements Bulider<Computer>{

    int cpu;
    int disk;
    int sceem;

    static ReentrantLock reentrantLock=new ReentrantLock();



    public DefaultComputerBuilder cpu(int cpu) throws IllegalStateException{
        if(cpu<=0){
            throw new IllegalStateException("cpu 规格大于0");//进行参数校验
        }
        this.cpu=cpu;
        return this;
    }

    public DefaultComputerBuilder disk(int disk) {

        this.disk=disk;

        return this;
    }

    public DefaultComputerBuilder sceem(int sceem) {

        this.sceem=sceem;

        return this;
    }

    public Computer build() {
        Computer computer=new Computer();
        computer.setCpu(cpu);
        computer.setDisk(disk);
        computer.setSceem(sceem);
        return computer;
    }
}



public class BuilderTest {

    public static void main(String[] args) {
        for(int i=0;i<10000;i++){

            new Thread(new MyRunnable().setI(i)).start();
        }
    }

}
class  MyRunnable implements Runnable{
    int i;

    public MyRunnable setI(int i) {
        this.i = i;
        return  this;
    }

    public void run() {

        Computer computer=new DefaultComputerBuilder().cpu(i).disk(i+1).sceem(i+2).build();
        System.out.println(computer);
    }
}

