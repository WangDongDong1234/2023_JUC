package com.wdd.ch42;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{
    //拆分差值不能超过10，计算10以内运算
    private static final Integer value =10;
    //拆分开始值
    private int begin;
    //拆分结束值
    private int end;
    //返回结果
    private int result;

    public MyTask(int begin,int end){
        this.begin = begin;
        this.end =end;
    }

    //拆分合并的过程
    @Override
    protected Integer compute() {
        //判断想家两个数值是否大于10
        if(end-begin<=value){
            //相加操作
            for(int i=begin;i<=end;i++){
                result=result+i;
            }
        }else{
            //进一步拆分
            int middle=(begin+end)/2;

            //拆分左边
            MyTask task01=new MyTask(begin,middle);
            //拆分右边
            MyTask task02=new MyTask(middle+1,end);
            //调用方法拆分
            task01.fork();
            task02.fork();
            result = task01.join()+task02.join();
        }
        return result;
    }
}
public class ForkJoin19 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(1,100);

        //创建分支合并池对象

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后结果
        Integer result =forkJoinTask.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}
