package com.wdd2.ch136;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 当一个对象已经计算过identity hashcode，它就无法进入偏向锁状态，跳过偏向锁，直接升级轻量级锁
 * 偏向锁在调用后hashcode方法后，升级为轻量级锁
 */
public class Demo23 {

    public static void main(String[] args) throws InterruptedException {
        //偏向锁，默认开启，默认4s延迟
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println("本应是偏向锁101");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //没有重写，一致性哈希，重写后无效,当一个对象已经计算过identity hash code，它就无法进入偏向锁状态;
        o.hashCode();
        synchronized (o){
            System.out.println("本应是偏向锁101，但是由于计算过一致性哈希，会直接升级为轻量级锁00");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
