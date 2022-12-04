package com.wdd.ch11;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share{
    private int number=0;
    //创建lock
    private Lock lock = new ReentrantLock();
    // 通知
    private Condition condition = lock.newCondition();

    public void incr(){
        lock.lock();
        try{
            //判断
            while (number!=0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decr(){
        lock.lock();
        try{
            //判断
            while (number!=1){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo06 {
    public static void main(String[] args) {


       Share share = new Share();
        //创建线程
        new Thread(()->{
            for(int i=0;i<10;i++){
                share.incr();
            }
        },"AA").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                share.decr();
            }
        },"BB").start();

        //创建线程
        new Thread(()->{
            for(int i=0;i<10;i++){
                share.incr();
            }
        },"CC").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                share.decr();
            }
        },"DD").start();
    }
}
