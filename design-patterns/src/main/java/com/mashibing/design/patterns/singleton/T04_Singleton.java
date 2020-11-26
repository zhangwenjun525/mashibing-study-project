package com.mashibing.design.patterns.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式   线程不安全
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/20 10:50
 */
public class T04_Singleton {

    private static T04_Singleton instance;

    private T04_Singleton(){

    }

    public static T04_Singleton getInstance() throws Exception{
        if(null == instance){
            synchronized (T04_Singleton.class){
                TimeUnit.MILLISECONDS.sleep(10);
                instance = new T04_Singleton();
            }
        }
        return instance;
    }

    public static void main(String[] args)  {
        for(int i = 0; i < 100; ++i){
            new Thread(() -> {
                try {
                    System.out.println(T04_Singleton.getInstance().hashCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
