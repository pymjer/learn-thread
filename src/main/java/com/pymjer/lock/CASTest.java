package com.pymjer.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/17 Time: 16:44
 */
public class CASTest {

    int i = 0;

    public void incr() {
        this.i ++;
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        final CASTest test = new CASTest();
        final AtomicReference<Integer> atomicReference = new AtomicReference<Integer>();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0 ; i< 10 ; i++ ) {
            Thread t = new Thread() {
                @Override
                public void run() {
                      for (int j = 0 ; j < 100000000; j++) {
                          while (!atomicReference.compareAndSet(null,100)) {
                          }
                          test.incr();
                          atomicReference.compareAndSet(100,null);
                      }
                    System.out.println(">>>>>>>>>>>>>>>>>>>");
                }
            };
            t.start();
            threads.add(t);
        }
        for (Thread thread: threads) {
            System.out.println("-----------------");
            thread.join();
        }
        System.out.println("最终结果：" + test.get());
    }
}
