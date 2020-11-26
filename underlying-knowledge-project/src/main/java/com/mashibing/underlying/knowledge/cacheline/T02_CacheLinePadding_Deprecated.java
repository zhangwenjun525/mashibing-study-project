package com.mashibing.underlying.knowledge.cacheline;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/10/22 9:09
 */
public class T02_CacheLinePadding_Deprecated {

    private static final long COUNT = 1_0000_0000L;

    private static class T {
        public volatile long p1, p2, p3, p4, p5, p6,p7;
        public volatile long x = 0L;
        public volatile long p8, p9, p10, p11, p12, p13,p14;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for(long i = 0; i < COUNT; ++i){
                arr[0].x = i;
            }

            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for(long i = 0; i < COUNT; ++i){
                arr[1].x = i;
            }

            latch.countDown();
        });

        final long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        latch.await();
        System.out.println(System.currentTimeMillis() - start);
    }
}
