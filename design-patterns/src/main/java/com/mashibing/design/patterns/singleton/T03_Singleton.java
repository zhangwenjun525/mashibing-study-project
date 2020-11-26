package com.mashibing.design.patterns.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式   线程安全
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/20 10:50
 */
public class T03_Singleton {

    private static T03_Singleton instance;

    private T03_Singleton(){

    }

    public static synchronized T03_Singleton getInstance() throws Exception{
        if(null == instance){
            TimeUnit.MILLISECONDS.sleep(10);
            instance = new T03_Singleton();
        }
        return instance;
    }

    public static void main(String[] args)  {
        for(int i = 0; i < 100; ++i){
            new Thread(() -> {
                try {
                    System.out.println(T03_Singleton.getInstance().hashCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
