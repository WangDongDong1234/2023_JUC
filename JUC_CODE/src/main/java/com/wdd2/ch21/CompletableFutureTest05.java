package com.wdd2.ch21;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例说明:电商比价需求，模拟如下情况:
 * 1需求:
 * 1.i同一款产品，同时搜索出同款产品在各大电商平台的售价;
 * 1.2同一款产品，同时搜索出本产品在同一个电商平台下，客个入驻卖家售价是多少2输出:出来结果希望是同款产品的在不同地方的价格清单列表，返回一个List<string>imysql》 in jd price is 88.05
 * mysqL》 in dangdang price is 86.11mysql》in taobao price is 90.433技术要求
 * 3.1 西数式编科I3.2链式编程
 * 3.3 Stream流式计算
 */
public class CompletableFutureTest05 {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );
    public static void main(String[] args) {
        long startTime =System.currentTimeMillis();
        List<String> list1=getPrice(list,"mysql");
        for(String element:list1){
            System.out.println(element);
        }
        long endTime =System.currentTimeMillis();
        System.out.println("总共花费："+(endTime-startTime));

        long startTime2 =System.currentTimeMillis();
        List<String> list2=getPriceByCompletableFuture(list,"mysql");
        for(String element:list2){
            System.out.println(element);
        }
        long endTime2 =System.currentTimeMillis();
        System.out.println("总共花费："+(endTime2-startTime2));
    }

    /**
     * 一个一个去查
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list,String productName){
       return list.stream().map(netMall -> String.format(productName+"in %s price is %.2f",netMall.getNetMallName(),netMall.calcPrice(productName))).collect(Collectors.toList());
    }

    /**
     * 万剑齐发
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list,String productName){
        return list.stream().map(
                netMall -> CompletableFuture.supplyAsync(()->
                        String.format(productName+"in %s price is %.2f",netMall.getNetMallName(),netMall.calcPrice(productName))
                )
        ).collect(Collectors.toList()).stream().map(s->s.join()).collect(Collectors.toList());
    }
}

class NetMall{

    @Setter
    @Getter
    private String netMallName;

    public NetMall(String netMallName){
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble()*2+productName.charAt(0);
    }


}
