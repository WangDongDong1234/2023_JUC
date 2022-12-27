package com.wdd2.ch81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber{
    public AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo13 {

    public static void main(String[] args) throws InterruptedException {

        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(50);

        for(int i =1;i<=50;i++){
            new Thread(()->{
                try{
                    for(int j=1;j<=1000;j++){
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }


            },String.valueOf(i)).start();
        }

        //等待10s，所有线程都计算完，才能获得最总结果500
        countDownLatch.await();
        System.out.println(myNumber.atomicInteger.get());

    }
}
