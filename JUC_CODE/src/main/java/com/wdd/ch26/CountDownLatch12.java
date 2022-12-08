package com.wdd.ch26;

import java.util.concurrent.CountDownLatch;

/**
 * 6个同学都离开教室，班长才能锁门
 */
public class CountDownLatch12 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        //6个同学陆续离开教室之后
        for(int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"走了");
                //计算-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //计数不为0等待
        countDownLatch.await();
        System.out.println("锁门");
    }
}
