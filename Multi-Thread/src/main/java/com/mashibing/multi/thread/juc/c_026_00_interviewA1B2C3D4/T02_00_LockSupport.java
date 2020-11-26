package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 11:12
 */
public class T02_00_LockSupport {

    static Thread t1, t2;

    public static void main(String[] args) throws InterruptedException {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for(char c : aC){
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");


        t2 = new Thread(() -> {
            for(char c : aI){
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println();
    }
}
