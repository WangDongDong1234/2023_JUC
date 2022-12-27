package com.wdd2.ch76;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * 题目:实现一个自旋锁，复习CAS思想
 * 自旋锁好处:循环比较获取没有类似wait的阻塞。
 * 通过CAs操作完成自旋锁，A线程先进来调月myLock方法自己持有锁5秒钟，B随后进来后发现*当前有线程持有锁，所以只能通过自旋等待，直到A释放策后B随后抢到。
 *
 */
public class SpinLockDemo12 {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
        //是null的话就占坑
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t"+"----task over,unlock...");

    }
    public static void main(String[] args) {
        SpinLockDemo12 spinLockDemo12 = new SpinLockDemo12();
        new Thread(()->{
            spinLockDemo12.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo12.unLock();
        },"A").start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo12.lock();
            spinLockDemo12.unLock();
        },"B").start();
    }
}
