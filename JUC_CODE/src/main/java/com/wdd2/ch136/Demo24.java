package com.wdd2.ch136;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁
 */
public class Demo24 {
    public static void main(String[] args) throws InterruptedException {
        //偏向锁，默认开启，默认4s延迟
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
       synchronized (o){
           o.hashCode();//没有重写，一致性hash，重写后无效
           System.out.println("偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式101，膨胀为重量级锁10");
           System.out.println(ClassLayout.parseInstance(o).toPrintable());
       }
    }
}
