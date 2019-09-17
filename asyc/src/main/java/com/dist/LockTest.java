package com.dist;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-22 9:58
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class LockTest {

   static ReentrantLock reentrantLock=new ReentrantLock();
    public static void out1(){
        reentrantLock.lock();
    }
    public static void out2(){
        reentrantLock.lock();
    }

    public static void main(String[] args) {
        out1();//不会抢占锁 这是一个共享锁
        out2();
    }
}
class LockTryCatch {


    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        try {
            //在自定义锁中存在异常的情况下 不建议在 try 中使用这么做
            lock.lock();
        }finally {
            lock.unlock();
        }
    }
}
class Mutex implements Lock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态设置为0
        protected boolean tryRelease(int releases) {
            if (getState() == 0) throw new
                    IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    public static void main(String[] args) {
        Mutex mutex=new Mutex();
        Thread thread1=new Thread(() -> {
            //
            mutex.lock();
            for (int i=0;i<1000;i++){
                System.out.println(Thread.currentThread().getName()+"-"+i);
            }
            mutex.unlock();
        });
        Thread thread2=new Thread(() -> {
            if(mutex.isLocked()){
                
            }
            mutex.lock();
            for (int i=0;i<1000;i++){
                System.out.println(Thread.currentThread().getName()+"-"+i);
            }
            mutex.unlock();
        });
        thread1.start();
        thread2.start();
    }
}

