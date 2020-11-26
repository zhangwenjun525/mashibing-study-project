package com.mashibing.multi.thread.juc.c_004;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/23 17:48
 */
public class T {

    private static int count = 10;

    //这里等同于synchronized(FineCoarseLock.class)
    public synchronized static void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        //考虑一下这里写synchronized(this)是否可以？
        synchronized(T.class) {
            count --;
        }
    }
}
