package com.wdd2.ch47;

import java.util.concurrent.TimeUnit;

public class Interrupt07 {

    public static void main(String[] args) {
        //实例方法interrupt()仅仅是设置线程的中断状态为true,不会立即停止线程

        Thread t1 = new Thread(()->{
            for(int i=1;i<300;i++){
                System.out.println("----:"+i);
            }
            System.out.println("t1线程调用interrupt()后的中断标识02："+Thread.currentThread().isInterrupted());
        },"t1");
        t1.start();

        System.out.println("t1线程默认的中断标识："+t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();//true
        System.out.println("t1线程调用interrupt()后的中断标识01："+t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //中断不活动的线程不会产生任何影响
        System.out.println("t1线程调用interrupt()后的中断标识03："+t1.isInterrupted());
    }
}
