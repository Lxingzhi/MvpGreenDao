package com.xz.greendao_xz.utils;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Android 线程池工具类
 *
 * 当一个任务通过execute(Runnable)方法欲添加到线程池时：
 * 1.如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
 * 2.如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 * 3.如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
 * 4.如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过
 * handler所指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize
 * ，如果三者都满了，使用handler处理被拒绝的任务。
 * 5.当线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 *
 * Created by ZhangYi on 2016/2/2.
 */
public class DefaultThreadPool {
    private final int CPU_COUNT = Runtime.getRuntime().availableProcessors();//CPU核心数
    private int CORE_POOL_SIZE = CPU_COUNT + 1 ; //线程池核心线程数
    private int MAX_POOL_SIZE = 2 * CPU_COUNT + 1 ; //非核心线程数
    private int KEEP_ALIVE_TIME = 1;  //非核心线程闲置时的超时时长
    //线程池的任务队列，通过线程池的execute方法提交的Runnable方法会保存在该队列中
    public static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(128);
    //线程工厂，为线程池提供创建新线程的功能。任务队列中的runnable会通过线程工厂创建为线程。
    private ThreadFactory threadFactory = new ThreadFactory() {
        private  final AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"myThreadPool Thread:"+integer.getAndIncrement());
        }
    };
    //线程池对拒绝任务的处理策略，被拒绝任务会调用该方法。
    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    };
    //初始化线程池对象
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,workQueue,threadFactory,handler);
    //构造方法改为private
    private DefaultThreadPool(){
    }
    //采用单例模式，保证该对象只会被创建一次
    private static DefaultThreadPool instance = null;
    public static DefaultThreadPool getInstance(){
        if(instance == null){
            instance = new DefaultThreadPool();
        }
        return instance;
    }

    /**
     * 移除一个任务
     * @param obj runable对象
     */
    public void removeTaskFromQueue(Runnable obj) {
        DefaultThreadPool.workQueue.remove(obj);
    }

    /**
     * 移除队列中的所有任务
     */
    public void removeAllTask(){
        DefaultThreadPool.workQueue.clear();
    }
    /**
     * 添加任务
     * @param runnable
     */
    public void execute(Runnable runnable){
        if(runnable != null) {
            try {
                threadPoolExecutor.execute(runnable);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭并等待任务执行完成,不接受新任务
     */
    public void shutDown(){
        if(threadPoolExecutor!=null){
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
        }
        instance = null;
    }

    public long getTaskCount(){
        if(threadPoolExecutor!=null){
            return threadPoolExecutor.getActiveCount();
        }else {
            return 0;
        }
    }

    /**
     * 关闭，立即关闭，并挂起所有正在执行的线程，不接受新任务
     */
    public void shutDownNow(){
        if(threadPoolExecutor != null){
            threadPoolExecutor.shutdownNow();
            try {
                threadPoolExecutor.awaitTermination(1, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor = null;
        }
        instance = null;
    }
}
