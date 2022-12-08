package com.wdd.ch32;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写锁降级为读锁
 */
public class Demo16 {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        //锁降级
        //1.获取写锁
        writeLock.lock();
        System.out.println("atguigu");

        //2.获取读锁
        readLock.lock();
        System.out.println("--read");

        //3.释放写锁
        writeLock.unlock();

        //4.释放读锁
        readLock.unlock();
    }
}
