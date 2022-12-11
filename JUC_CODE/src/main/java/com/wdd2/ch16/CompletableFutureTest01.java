package com.wdd2.ch16;

import java.util.concurrent.*;

public class CompletableFutureTest01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            //没有指定线程池用ForkJoinPool.commonPool-worker-1
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(completableFuture1.get());

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            //指定线程池pool-1-thread-1
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },threadPool);
        threadPool.shutdown();
        System.out.println(completableFuture2.get());

        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            //没有指定线程池用ForkJoinPool.commonPool-worker-1
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "demo3";
        });
        System.out.println(completableFuture3.get());

        ExecutorService threadPool2 = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            //指定线程池pool-2-thread-1
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "demo4";

        },threadPool2);
        threadPool.shutdown();
        System.out.println(completableFuture4.get());
    }
}
