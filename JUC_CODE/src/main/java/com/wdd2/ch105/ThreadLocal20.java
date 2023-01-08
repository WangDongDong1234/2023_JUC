package com.wdd2.ch105;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MyObject{

    //这个方法一般不用复写，我们只是为了教学给大家演示案例做说明
    @Override
    protected void finalize() throws Throwable {
        //finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-----invoke finalize method~!!!");
    }
}

/**
 * -Xms10m -Xmx10m
 */
public class ThreadLocal20 {
    public static void main(String[] args) throws InterruptedException {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        System.out.println(phantomReference.get());

        List<byte[]>  list = new ArrayList<>();
        new Thread(()->{
            while (true){
                list.add(new byte[1*1024*1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                System.out.println("t2");
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference!=null){
                    System.out.println("有虚对象回收加入了队列");
                    break;
                }

            }
        },"t2").start();

    }

    private static void weakReference() throws InterruptedException {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.gc(); //人工开启GC，一般不用
        TimeUnit.SECONDS.sleep(3);
        System.out.println("----gc after内存够用："+weakReference.get());
    }

    private static void softReference() throws InterruptedException {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.gc(); //人工开启GC，一般不用
        TimeUnit.SECONDS.sleep(3);
        System.out.println("----gc after内存够用："+softReference.get());

        try{
            byte[] bytes = new byte[20*1024*1024];//20MB对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("----gc after内存不够用："+softReference.get());
        }
    }

    private static void strongReference() throws InterruptedException {
        MyObject myObject = new MyObject();
        System.out.println("gc before:"+myObject);

        myObject = null;
        System.gc(); //人工开启GC，一般不用
        TimeUnit.SECONDS.sleep(3);
        System.out.println("gc after:"+myObject);
    }
}
