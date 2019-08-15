试图实现对 异步的控制 实现后台的能够 控制线程的 执行 ，并且 通过前端异步的方式可以实现 实时把处理数据进行返回
线程池执行 一个任务的过程 如下 
![avatar](https://i.loli.net/2019/05/08/5cd1d2ac0936c.jpg)
线程池中状态变化
![avatar](https://i.loli.net/2019/05/08/5cd1d2aa81655.jpg)


## Callable
    1.5 之前 我们运行的实质的 有 runnable 接口中的run方法进行调用 并且进行数据返回的
     
    1.5 之后新增了 Callable 能够 抛出 异常 并且 有 返回值 
##  FutureTask
    其实就是一个runnable，但是又继承了 callable 并且将callable 放在run中执行，并且将结果 一个全局变量 

##  Future
    Future 这个接口封装了 对 执行结果对的获取 以及 对 执行过程的 控制
   
##  Executor
    它将任务的提交与任务的执行分离开来，
    是一个线程管理框架 ，提供了另一种 开启线程的方式
    如果使用new Thread(...).start()的方法处理多线程，有如下缺点：
    
    开销大。对于JVM来说，每次新建线程和销毁线程都会有很大的开销。
    线程缺乏管理。没有一个池来限制线程的数量，如果并发量很高，
    会创建很多的线程，而且线程之间可能会有相互竞争，
    这将会过多得占用系统资源，增加系统资源的消耗量。
    而且线程数量超过系统负荷，容易导致系统不稳定。
    使用线程池的方式，有如下优点：
    
    复用线程。通过复用创建的了的线程，减少了线程的创建、消亡的开销。
    有效控制并发线程数。
    提供了更简单灵活的线程管理。可以提供定时执行、定期执行、单线程、可变线程数等多种线程使用功能。
    
##  ExecutorService
    是功能更加强大的任务执行容器

##  Executors
    是更上一层的容器 可以返回特定的线程池实例
    
##  ThreadPoolExecutor
    最基本的线程池， Executors框架提供的各种线程池类 都是通过 这个线程池 实现出来的 
    corePoolSize
    
    核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
    
    maximumPoolSize
    
    线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
    
    keepAliveTime
    
    非核心线程的闲置超时时间，超过这个时间就会被回收。
    
    unit
    
    指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
    
    workQueue
    
    线程池中的任务队列.
    
    常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
    
    threadFactory
    
    线程工厂，提供创建新线程的功能。ThreadFactory是一个接口，只有一个方法

     
## 如何配置线程
流程聊完了再来看看上文提到了几个核心参数应该如何配置呢？

有一点是肯定的，线程池肯定是不是越大越好。

通常我们是需要根据这批任务执行的性质来确定的。

IO 密集型任务：由于线程并不是一直在运行，所以可以尽可能的多配置线程，比如 CPU 个数 * 2
CPU 密集型任务（大量复杂的运算）应当分配较少的线程，比如 CPU 个数相当的大小。

##  使用 actuator 进行线程的监控 
##  使用 hystrix  实现线程的隔离

    
    
    
    