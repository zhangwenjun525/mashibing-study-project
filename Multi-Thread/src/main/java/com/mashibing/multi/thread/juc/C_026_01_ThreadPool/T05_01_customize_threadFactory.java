package com.mashibing.multi.thread.juc.C_026_01_ThreadPool;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/25 15:24
 */
public class T05_01_customize_threadFactory {

    class MyThreadFactory implements ThreadFactory{

        private final String poolName;

        public MyThreadFactory(String poolName) {
            this.poolName = poolName;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, poolName);
        }
    }
}
