package ru.gb.zettro.lesson2;


public interface MyList<E> {

    void add(E e);
    void addAtHead(E e);
    void addAtTail(E e);
    void add(int index, E element);
    E remove(E e);
    E remove(int index);
    E get(int index);
    E set(int index, E element);
    boolean contains(E e);
    int size();
    boolean isEmpty();
    void clear();
    int indexOf(E e);

}
