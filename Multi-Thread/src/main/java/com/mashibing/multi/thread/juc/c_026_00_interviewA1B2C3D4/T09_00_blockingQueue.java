package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 14:09
 */
public class T09_00_blockingQueue {

    static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for(char c : aC){
                try {
                    System.out.print(c);
                    queue1.put("ok");
                    queue2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for(char c : aI){
                try {
                    queue1.take();
                    System.out.print(c);
                    queue2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();

    }


}
