package com.wdd.ch30;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class MyCache{

    //创建map集合
    private volatile Map<String,Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写操作"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value );
            System.out.println(Thread.currentThread().getName()+"写结束"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public Object get(String key){

        readWriteLock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName()+"正在读操作"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            o=map.get(key);
            System.out.println(Thread.currentThread().getName()+"读结束"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

        return o;
    }


}

/**
 * 出现还没写完就读
 */
public class ReadWriteLock15 {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i=1;i<=5;i++){
            final int num =i;
            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }

        for(int i=6;i<=10;i++){
            final int num =i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
