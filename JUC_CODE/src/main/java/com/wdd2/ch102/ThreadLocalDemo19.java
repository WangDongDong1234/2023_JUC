package com.wdd2.ch102;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyData{
    ThreadLocal<Integer> threadLocalFiel = ThreadLocal.withInitial(()->0);

    public void add(){
        threadLocalFiel.set(threadLocalFiel.get()+1);
    }
}

/**
 * .【强制】必须回收自定义的 ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的 ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用
 * try-finally块进行回收。
 */
public class ThreadLocalDemo19 {

    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try{
            for(int i=0;i<10;i++){
                threadPool.submit(()->{
                    Integer breforInt =myData.threadLocalFiel.get();
                    myData.add();
                    Integer afterInt =myData.threadLocalFiel.get();
                    System.out.println(Thread.currentThread().getName()+"\t"+"breforInt:"+breforInt+",afterInt:"+afterInt);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
