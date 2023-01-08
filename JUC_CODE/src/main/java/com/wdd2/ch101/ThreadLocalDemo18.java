package com.wdd2.ch101;


import java.util.Random;
import java.util.concurrent.TimeUnit;



/**
 * 需求2:5个销售卖完随机数房子，各自独立鹃售额度，自己业绩按提成走，分灶吃饭，各个销售自己动手，丰衣足食
 */
public class ThreadLocalDemo18 {

    public static void main(String[] args) throws InterruptedException {

        House house = new House();

        for(int i=1;i<=5;i++){
            new Thread(()->{
                int size = new Random().nextInt(5)+1;
                try{
                    for(int j=1;j<=size;j++){
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+"号销售卖出："+house.saleVolume.get());
                }finally {
                    house.saleVolume.remove();
                }
            },String.valueOf(i)).start();
        }


    }
}
