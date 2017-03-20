package com.pymjer.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/17 Time: 17:28
 */
public class TicketLock {
  AtomicInteger serverNum = new AtomicInteger();
  AtomicInteger clientNum = new AtomicInteger();
  ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();
  public void lock() {
      int ticket = clientNum.getAndIncrement();
      LOCAL.set(ticket);
      while (ticket != serverNum.get()) {
      }
  }

  public void unlock() {
      Integer ticket = LOCAL.get();
      serverNum.compareAndSet(ticket,ticket + 1);
  }
}