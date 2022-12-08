package com.wdd.ch27;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier13 {

    private static final int num=7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num,()->{
            System.out.println("龙珠收齐，可以许愿");
        });

        for(int i=1;i<=7;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"星龙珠找到");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
