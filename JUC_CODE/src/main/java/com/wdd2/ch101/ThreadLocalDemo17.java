package com.wdd2.ch101;


import java.util.Random;
import java.util.concurrent.TimeUnit;



/**
 * 需求1:5个销售卖房子，集团高层只关心销售总量的准确统计数。
 */
public class ThreadLocalDemo17 {

    public static void main(String[] args) throws InterruptedException {

        House house = new House();

        for(int i=1;i<=5;i++){
            new Thread(()->{
                int size = new Random().nextInt(5)+1;
                System.out.println(size);
                for(int j=1;j<=size;j++){
                    house.saleHouse();
                }
            },String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(3);

        System.out.println(Thread.currentThread().getName()+"\t"+"共计卖出多少套："+house.saleCount);
    }
}
