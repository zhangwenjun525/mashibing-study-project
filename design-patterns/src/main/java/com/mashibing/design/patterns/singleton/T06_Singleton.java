package com.mashibing.design.patterns.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 静态内部类方式实现单例模式
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/20 10:50
 */
public class T06_Singleton {

    private T06_Singleton(){

    }

    static class SingletonHolder{

        private final static T06_Singleton INSTANCE = new T06_Singleton();
    }


    public static T06_Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args)  {
        for(int i = 0; i < 100; ++i){
            new Thread(() -> {
                try {
                    System.out.println(T06_Singleton.getInstance().hashCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
