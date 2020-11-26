package com.mashibing.design.patterns.iterator;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:19
 */
public interface Collection<E> {

    void add(E e);

    int size();

    Iterator<E> iterator();
}
