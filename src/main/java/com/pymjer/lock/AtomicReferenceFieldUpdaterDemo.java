package com.pymjer.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/17 Time: 18:23
 */
public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Man.class,String.class,"name");
        Man hj = new Man();
        updater.compareAndSet(hj,hj.name,"huangjiang");

        System.out.println("新的名字：" + hj.name);
    }

}

class Man {
    volatile String name = "hj";
}
