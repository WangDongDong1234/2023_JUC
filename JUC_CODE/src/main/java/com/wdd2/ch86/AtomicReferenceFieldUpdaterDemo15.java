package com.wdd2.ch86;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 资源类
 */
class MyVar{
    public volatile Boolean isInit =Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar,Boolean> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInit");

    public void init(MyVar myVar){
        if(atomicReferenceFieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)){
            System.out.println(Thread.currentThread().getName()+"\t"+"-------start init,need 2 seconds");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+"-------start init");
        }else{
            System.out.println(Thread.currentThread().getName()+"\t"+"已经有线程进行初始化工作");
        }
    }
}

/**
 * 需求:
 * 多线程并发调用一个类的初始化方法，
 * 如果未被初始化过，将执行初始化工作，
 * 要求只能被初始化一次，只有一个线程操作成功l
 */
public class AtomicReferenceFieldUpdaterDemo15 {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for(int i =0;i<50;i++){
            new Thread(()->{
                myVar.init(myVar);
            },String.valueOf(i)).start();
        }
    }
}
