package com.wdd2.ch125;

/**
 * 卖票,当一段同步代码一直被同一个线程多次访问
 */
public class SaleTicketDemo22 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for(int i=0;i<55;i++){
                ticket.sale();
            }
        },"a").start();
        new Thread(()->{
            for(int i=0;i<55;i++){
                ticket.sale();
            }
        },"b").start();
        new Thread(()->{
            for(int i=0;i<55;i++){
                ticket.sale();
            }
        },"c").start();
        new Thread(()->{
            for(int i=0;i<55;i++){
                ticket.sale();
            }
        },"d").start();
    }
}

class Ticket{
    private int number =50;

    Object lockObject = new Object();

    public void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"卖出第：\t"+(number--)+"\t 还剩下："+number);
        }
    }
}
