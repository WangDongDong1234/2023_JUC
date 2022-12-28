package com.wdd2.ch89;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class ClickNumber{
    int number =0;
    public synchronized void clickBySynchronized(){
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong(){
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder(){
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x,y)->x+y,0);
    public void clickByLongAccumulator(){
        longAccumulator.accumulate(1);
    }
}

/**
 * 需求: 50个线程，每个线程10ow次，总点赞数出来
 */
public class AccumulatorCompareDemo16 {

    public static final int _1W=10000;
    public static final int threadNumber =50;
    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;

        CountDownLatch countDownLatch1= new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2= new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3= new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4= new CountDownLatch(threadNumber);

        startTime = System.currentTimeMillis();
        for(int i =1;i<=threadNumber;i++){
            new Thread(()->{
                try{
                    for(int j=1;j<=100*_1W;j++){
                        clickNumber.clickBySynchronized();
                    }
                }finally {
                    countDownLatch1.countDown();
                }

            }).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("--cost time -clickBySynchronized:"+(endTime-startTime));

        startTime = System.currentTimeMillis();
        for(int i =1;i<=threadNumber;i++){
            new Thread(()->{
                try{
                    for(int j=1;j<=100*_1W;j++){
                        clickNumber.clickByAtomicLong();
                    }
                }finally {
                    countDownLatch2.countDown();
                }

            }).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("--cost time -clickByAtomicLong:"+(endTime-startTime));

        startTime = System.currentTimeMillis();
        for(int i =1;i<=threadNumber;i++){
            new Thread(()->{
                try{
                    for(int j=1;j<=100*_1W;j++){
                        clickNumber.clickByLongAdder();
                    }
                }finally {
                    countDownLatch3.countDown();
                }

            }).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("--cost time -clickByLongAdder:"+(endTime-startTime));

        startTime = System.currentTimeMillis();
        for(int i =1;i<=threadNumber;i++){
            new Thread(()->{
                try{
                    for(int j=1;j<=100*_1W;j++){
                        clickNumber.clickByLongAccumulator();
                    }
                }finally {
                    countDownLatch4.countDown();
                }

            }).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("--cost time -clickByLongAccumulator:"+(endTime-startTime));
    }
}
