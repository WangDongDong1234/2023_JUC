package com.wdd.ch35;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueue17 {
    public static void main(String[] args) {

        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.add("e"));


    }
}
