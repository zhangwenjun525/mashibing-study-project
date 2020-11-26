package com.mashibing.underlying.knowledge.cacheline;

import java.util.concurrent.CountDownLatch;

/**
 * 执行需要加上 JVM -XX:-RestrictContended 才有效果
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/10/22 9:09
 */
public class T03_CacheLinePadding_Deprecated {

    private static final long COUNT = 1_0000_0000L;

    @sun.misc.Contended
    private static class T {
        public volatile long x = 0L;
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
