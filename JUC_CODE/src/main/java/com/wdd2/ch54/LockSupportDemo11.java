package com.wdd2.ch54;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 正常场景
 */
public class LockSupportDemo11 {
    public static void main(String[] args) {

      Thread t1=  new Thread(()->{
          try {
              TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName()+"\t ----come in");
          LockSupport.park();
          System.out.println(Thread.currentThread().getName()+"\t ----被唤醒");

       },"t1");
      t1.start();



        new Thread(()->{
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName()+"\t ----发出通知");

        },"t2").start();
    }
}
