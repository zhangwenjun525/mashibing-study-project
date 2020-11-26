package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.util.concurrent.TimeUnit;

/**
 * 通过一个中间变量判断让T1线程先执行
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 11:26
 */
public class T04_00_sync_wait_notify {

    final static Object o = new Object();

    volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o){
                try {
                    for(char c : aC){
                        if(!flag){
                            o.wait();
                        }
                        flag = false;
                        System.out.print(c);
                        o.notify();
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
                        if(flag){
                            o.wait();
                        }
                        flag = true;
                        System.out.print(c);
                        o.notify();
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
