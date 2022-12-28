package com.wdd2.ch85;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

//资源类
class BankAccount{
    String bankName ="CCB";

    public volatile int money =0;

    public  synchronized void add(){
        money++;
    }

    //因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须
    //使用静态方法newupdater()创建一个更新器，并且需要设置想要更新的类和属性。

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    //不加synchronized，保证高性能原子性，局部微创小手术
    public void transMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

/**
 * 以一种线程安全的方式操作非线程安全对象的某些字段。
 * 需求:
 * 10个线程，
 * 每个线程转账1000,
 * 不使用synchronized,尝试使用AtomicIntegerFieldupdater来实现。
 */
public class AtomicIntegerFieldUpdaterDemo14 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                try{
                    for(int j=0;j<1000;j++){
                        //bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                }finally {
                    //要放到线程里面去做减法，不能放在线程外面
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(bankAccount.money);
    }
}
