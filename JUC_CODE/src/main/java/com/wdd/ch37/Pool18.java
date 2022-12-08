package com.wdd.ch37;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示线程池三种常用分类
public class Pool18 {
    public static void main(String[] args) {

        //一池五线程
        ExecutorService poo1 = Executors.newFixedThreadPool(5);

        //一池一线程
        // ExecutorService poo2 = Executors.newSingleThreadExecutor();

        //自定义扩展
        ExecutorService poo3 = Executors.newCachedThreadPool();

        try{
            for(int i=0;i<10;i++){
                poo3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭线程池
            poo3.shutdown();
        }
    }
}
