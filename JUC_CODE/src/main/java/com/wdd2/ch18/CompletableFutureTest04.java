package com.wdd2.ch18;

import java.util.concurrent.*;

public class CompletableFutureTest04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try{
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
                System.out.println(Thread.currentThread().getName()+"---come in");
                int result = ThreadLocalRandom.current().nextInt(10);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---1秒钟后出结果："+result);
                return result;
            },threadPool).whenComplete((v,e)->{
                //v 指的是result, e是异常
                if(e==null){
                    System.out.println("---计算完成 ，更新系统updateVa："+v);
                }
            }).exceptionally(e->{
                e.printStackTrace();
                System.out.println("异常情况是："+e.getStackTrace()+"\t"+e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName()+"线程先去忙其它任务");
        }catch (Exception e){

        }finally {
            threadPool.shutdown();
        }



    }
}
