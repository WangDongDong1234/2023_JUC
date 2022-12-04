package com.wdd.ch07;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 第一步： 创建资源类，定义属性和操作方法
 */
class LTicket{
    //票数
    private int number=30;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try{
            //判断：是否有票
            if(number>0){
                System.out.println(Thread.currentThread().getName()+":卖出："+(number--)+"剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
public class SaleTicket03 {
    public static void main(String[] args) {
        //创建Ticket对象
        LTicket ticket = new LTicket();

        //创建三个线程
        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"DD").start();

        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"EE").start();

        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"FF").start();


    }
}
