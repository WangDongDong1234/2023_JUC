package com.wdd.ch04;

public class Main01 {
    public static void main(String[] args) {
        Thread aa =new Thread(()->{
            //Thread.currentThread().isDaemon() 守护线程返回true,用户线程返回false
            System.out.println(Thread.currentThread().getName()+"::"+Thread.currentThread().isDaemon());
            while (true){

            }
        },"aa");

        // 将该线程设置为守护线程
        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName()+"over");

    }
}
