package com.wdd2.ch51;

import java.util.concurrent.TimeUnit;

/**
 * 正常场景
 */
public class LockSupportDemo09 {
    public static void main(String[] args) {
        Object objectLock = new Object();

        new Thread(()->{
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName()+"\t----come in");

                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"\t ----被唤醒");
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            synchronized (objectLock){
               objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"\t ----发出通知");
            }
        },"t2").start();

    }
}
