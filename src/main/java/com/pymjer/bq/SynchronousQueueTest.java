package com.pymjer.bq;

import java.util.concurrent.SynchronousQueue;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/7 Time: 9:42
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        Thread putThread = new Thread(){
            @Override
            public void run() {
                for(int i = 0 ; i < 20000; i++) {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName() + "放入:" + i );
                        queue.put(String.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread takeThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    String take = null;
                    try {
                        take = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + "得到:" + take );
                }
            }
        };
        putThread.start();
        takeThread.start();
        putThread.join();
        takeThread.join();
    }
}
