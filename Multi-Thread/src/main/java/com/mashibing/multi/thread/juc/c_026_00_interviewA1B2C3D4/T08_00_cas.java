package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 13:59
 */
public class T08_00_cas {

    enum ReadyToRun{
        T1, T2
    }

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for(char c : aC){
                while (ReadyToRun.T2.equals(r)){ }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for(char c : aI){
                while (ReadyToRun.T1.equals(r)){}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();

    }




}
