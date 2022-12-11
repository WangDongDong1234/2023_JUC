package com.wdd2.ch17;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
        }).whenComplete((v,e)->{
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
        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭，暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
