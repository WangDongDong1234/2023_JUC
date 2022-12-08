package com.wdd.ch23;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        return 200;
    }
}
public class Demo11 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //方式一
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        //方式二
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            return 201;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();

        while (!futureTask2.isDone()){
            System.out.println("wait");
        }
        System.out.println(futureTask2.get());
    }
}
