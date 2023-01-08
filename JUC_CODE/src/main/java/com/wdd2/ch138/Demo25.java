package com.wdd2.ch138;

/**
 * 锁消除
 * 从J工T角度看相当于无视它，synchronized (o)不存在了，这个锁对象并没有被共用扩散到其它线程使用，
 * 极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 */
public class Demo25 {
    static Object objectLock = new Object();
    public void m1(){
//        synchronized (objectLock){
//            System.out.println("------hello lockClearUpDemo");
//        }

        //锁消除问题，JIT编译器会无视它,因为这属于人手一把手机，不存在冲突
        Object o= new Object();
        synchronized (o){
            System.out.println("------hello lockClearUpDemo"+"\t"+o.hashCode()+"\t"+objectLock.hashCode());
        }
    }
    public static void main(String[] args) {
        Demo25 lockClearUPDemo = new Demo25();
        for(int i=1;i<=10;i++){
            new Thread(()->{
                lockClearUPDemo.m1();
            },String.valueOf(i)).start();
        }
    }
}
