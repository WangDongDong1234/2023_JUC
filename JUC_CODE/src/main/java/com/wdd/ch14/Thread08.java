package com.wdd.ch14;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全
 */
public class Thread08 {
    public static void main(String[] args) {
        //创建ArrayList集合

        //List<String> list= new ArrayList<>();

        //List<String> list= new Vector<>();

        //List<String> list=Collections.synchronizedList(new ArrayList<>());

        List<String> list=new CopyOnWriteArrayList<>();

        for(int i=0;i<300;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
