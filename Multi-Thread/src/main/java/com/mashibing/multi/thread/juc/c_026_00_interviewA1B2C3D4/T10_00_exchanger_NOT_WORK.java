package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.util.concurrent.Exchanger;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 14:24
 */
public class T10_00_exchanger_NOT_WORK {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            for(int i=0; i<aI.length; i++) {
                System.out.print(aI[i]);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0; i<aC.length; i++) {
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(aC[i]);
            }
        }).start();
    }
}
