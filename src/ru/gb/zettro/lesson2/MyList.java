package ru.gb.zettro.lesson2;


public interface MyList<E> {

    void add(E e);
    void addAtHead(E e);
    void addAtTail(E e);
    E remove(E e);
    boolean contains(E e);
    int size();
    boolean isEmpty();
    void clear();
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    E remove(int index);
    int indexOf(E e);

}
