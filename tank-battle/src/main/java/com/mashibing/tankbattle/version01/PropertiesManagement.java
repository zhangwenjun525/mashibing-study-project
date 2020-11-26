package com.mashibing.tankbattle.version01;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/19 19:41
 */
public class PropertiesManagement {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(PropertiesManagement.class.getClassLoader().getResourceAsStream("config"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Object initialization_count = properties.get("initialization_count");
        System.out.println(initialization_count);
    }
}
