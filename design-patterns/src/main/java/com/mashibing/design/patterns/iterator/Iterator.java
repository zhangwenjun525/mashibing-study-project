package com.mashibing.design.patterns.iterator;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:20
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();
}
