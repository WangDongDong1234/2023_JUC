package com.wdd.ch09;

/**
 * 第一步： 创建资源类，定义属性和操作方法
 */
class Share{

    //初始值
    private int number =0;

    //+1方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断，干活，通知
        if(number!=0){// 判断number值是否是0，如果不是0，等待
            this.wait();
        }

        //如果number值是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他线程
        this.notify();
    }

    //-1方法
    public synchronized void decr() throws InterruptedException {
        //第二步 判断，干活，通知
        if(number!=1){// 判断number值是否是0，如果不是0，等待
            this.wait();
        }

        //如果number值是1，就-1操作
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他线程
        this.notify();
    }
}
public class ThreadDemo04 {
    public static void main(String[] args) {
        Share share = new Share();

        //创建线程
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
