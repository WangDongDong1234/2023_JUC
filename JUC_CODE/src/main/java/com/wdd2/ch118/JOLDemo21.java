package com.wdd2.ch118;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLDemo21 {

    public static void main(String[] args) {
        //VM的细节详情情况
        //System.out.println(VM.current().details());

        //所有的对象分配的字节数都是8的整数倍
        //System.out.println(VM.current().objectAlignment());

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

//只有一个对象头的实例对象，16字节（忽略压缩指针的影响)+4字节+1字节=-21字节----》对其填充，24字节
class Customer{
    int id;
    boolean flag = false;
}

