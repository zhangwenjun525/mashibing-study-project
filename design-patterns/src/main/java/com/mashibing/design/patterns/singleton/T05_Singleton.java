package com.mashibing.design.patterns.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式   线程不安全
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/20 10:50
 */
public class T05_Singleton {

    // volatile 防止指令重排
    private static volatile T05_Singleton instance;

    private T05_Singleton(){

    }

    public static T05_Singleton getInstance() throws Exception{
        if(null == instance){
            synchronized (T05_Singleton.class){
                if(null == instance){
                    TimeUnit.MILLISECONDS.sleep(10);
                    instance = new T05_Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args)  {
        for(int i = 0; i < 100; ++i){
            new Thread(() -> {
                try {
                    System.out.println(T05_Singleton.getInstance().hashCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
