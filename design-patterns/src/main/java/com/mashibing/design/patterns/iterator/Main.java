package com.mashibing.design.patterns.iterator;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:57
 */
public class Main {

    public static void main(String[] args) {
        Collection<String> list = new LinkedList<>();

        for(int i = 0; i < 15; ++i){
            list.add(new String("s" + i));
        }

        System.out.println(list.size());

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
