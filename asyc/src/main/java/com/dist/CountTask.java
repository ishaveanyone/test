package com.dist;

import java.util.concurrent.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-12 10:44
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 5390823896306412900L;

    private static final int THRESHOLD = 2; //阈值
    private Integer start;
    private Integer end;

    public CountTask(Integer start, Integer end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer sum = Integer.valueOf(0);
        //如果任务足够小就计算任务
        boolean canCompute = (end-start) <= THRESHOLD;
        if(canCompute) {
            for(int i=start; i<=end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阀值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle+1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待子任务执行完，并得到其结果
            Integer leftResult = (Integer) leftTask.join();
            Integer rightResult = (Integer) rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
    /*
    * 根据给定的/所有可用的并行等级，创建一个拥有足够的线程数目的线程池(ForkJoinPool)。
    * 或许会使用多重队列来降低冲突。并行的等级是和运行的最大线程数目相关。
    * 真实的线程数目或许会动态地增长和收缩。一个工作窃取的线程池对于提交的任务不能保证是顺序执行的。
    * */
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
        //生成一个计算任务，负责计算1+2+3+4+...+99+100
        CountTask task = new CountTask(1, 100);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
