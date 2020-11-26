package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 不能保证一定是A1B2C3..., 也有可能是1A2B3C4D5E6F7G
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 11:26
 */
public class T03_00_sync_wait_notify {

    final static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
/*            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (o){
                try {
                    for(char c : aC){
                        System.out.print(c);
                        o.notify();
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o){
                try {
                    for(char c : aI){
                        System.out.print(c);
                        o.notify();
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify();
            }
        }, "t2").start();

        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println();
    }

}
