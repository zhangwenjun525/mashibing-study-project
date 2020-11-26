package com.mashibing.design.patterns.iterator;

/**
 * @author zhangwj
 * @version 1.0
 * @date 2020/11/26 9:34
 */
public class LinkedList<E> implements Collection<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int size = 0;

    @Override
    public void add(E e) {
        Node node = new Node(e);
        if(head == null || tail == null){
            head = new Node(null);
            tail = new Node(null);
            head.next = node;
            tail.next = node;
        }else {
            tail.next.next = node;
            tail.next = node;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<E>();
    }

    private class Node<E>{
        private E e;
        private Node<E> next;

        public Node(E e) {
            this.e = e;
        }
    }

    private class LinkedListIterator<E> implements Iterator<E> {

        private Node currentNode = head;


        @Override
        public boolean hasNext() {
            return currentNode != null && currentNode.next != null;
        }

        @Override
        public E next() {
           currentNode = currentNode.next;
           return (E) currentNode.e;
        }
    }

}
