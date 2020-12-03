package com.mashibing.jvm.classloader;

public class T010_Parent {
    private static T006_MSBClassLoader parent = new T006_MSBClassLoader();

    private static class MyLoader extends ClassLoader {
        public MyLoader() {
            super(parent);
        }
    }
}
