package com.pymjer.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/17 Time: 17:30
 */
public class TicketLockDemo {

    int i = 0;

    public void incr() {
        this.i ++;
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        final TicketLock lock = new TicketLock();
        final TicketLockDemo demo = new TicketLockDemo();
        List<Thread> threads = new ArrayList<Thread>();
        long start = System.currentTimeMillis();
        for (int i = 0 ;i < 10 ;i ++) {
            Thread  t = new Thread() {
                @Override
                public void run() {
                    for (int j = 0 ; j < 100000; j++) {
                        lock.lock();
                        demo.incr();
                        lock.unlock();
                    }
                }
            };
            t.start();
            threads.add(t);
        }
        for (Thread t: threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行完成，共耗时：" + (end - start) + " ms");
    }
}
