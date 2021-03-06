package com.mashibing.multi.thread.juc.c_026_00_interviewA1B2C3D4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 12:45
 */
public class T06_00_lock_condition {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {

            lock.lock();
            try{
                for(char c : aC){
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try{
                for(char c : aI){
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t2").start();

    }

}
