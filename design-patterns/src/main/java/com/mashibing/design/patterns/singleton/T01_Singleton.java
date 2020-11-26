package com.mashibing.design.patterns.singleton;

import java.io.Serializable;

/**
 * 饿汉式   线程安全
 *
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/20 10:47
 */
public class T01_Singleton implements Serializable {

    private static final T01_Singleton INSTANCE = new T01_Singleton();

    private T01_Singleton(){
        if(INSTANCE != null) {
            throw new RuntimeException("xxxxx");
        }
    }

    public static T01_Singleton getInstance(){
        return INSTANCE;
    }


    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        T01_Singleton s1 = getInstance();
        T01_Singleton s2 = getInstance();

        System.out.println(s1 == s2);
    }
}
