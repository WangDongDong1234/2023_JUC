package com.wdd.ch18;

import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendSMS() throws Exception{
        System.out.println("----------sendSMS");
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println("----------sendEmail");
    }

    public void getHello(){
        System.out.println("------getHello");
    }
}
public class Lock09 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1= new Phone();


        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(()->{
            try {
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

    }
}
