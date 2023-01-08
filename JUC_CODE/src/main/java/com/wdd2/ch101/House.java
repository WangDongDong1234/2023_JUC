package com.wdd2.ch101;

public class House {
    int saleCount =0;

    public synchronized  void saleHouse(){
        ++saleCount;
    }

//    /**
//     * 每个线程初始化为0（方式1）
//     */
//    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };

    /**
     * 每个线程初始化为0（方式2-  推荐）
     */
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()->0);

    public void saleVolumeByThreadLocal(){
        saleVolume.set(saleVolume.get()+1);
    }
}
