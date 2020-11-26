package com.mashibing.design.patterns.iterator;

import java.util.Objects;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:25
 */
public class ArrayList<E> implements Collection<E> {

    E[] objects = (E[])new Object[10];

    private int index = 0;

    @Override
    public void add(E e) {
       if(index == objects.length){
           E[] newObjects = (E[]) new Object[objects.length * 2];
           System.arraycopy(objects, 0, newObjects, 0, objects.length);
           objects = newObjects;
       }

       objects[index] = e;
       index++;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<E>();
    }

    private class ArrayListIterator<E> implements Iterator<E>{

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < index;
        }

        @Override
        public E next() {
            E e = (E) objects[currentIndex];
            currentIndex++;
            return e;
        }
    }
}
