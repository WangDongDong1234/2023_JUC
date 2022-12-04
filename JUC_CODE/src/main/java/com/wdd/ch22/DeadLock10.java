package com.wdd.ch22;

public class DeadLock10 {

    static Object a= new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName()+"持有a，试图获取b");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"获取b");
                }
            }
        },"AA").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName()+"持有b，试图获取a");
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"获取a");
                }
            }
        },"BB").start();
    }
}
