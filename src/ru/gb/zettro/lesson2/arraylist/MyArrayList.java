package ru.gb.zettro.lesson2.arraylist;


import ru.gb.zettro.lesson2.MyList;

import java.util.NoSuchElementException;

public class MyArrayList<E> implements MyList<E> {


    private static final int DEFAULT_CAPACITY = 32;
    private Object[] array;
    private int size;
    private int capacity;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }


    // Searches list for an element that equals to <e>.
    // Returns index of found element, otherwise returns -1.
    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public void add(E e) {
        addAtTail(e);
    }

    @Override
    public void addAtTail(E e) {
        checkCapacity();
        array[size++] = e;
    }


    @Override
    public void addAtHead(E e) {
        add(0, e);
    }


    // Inserts <element> at <index> position, thus shifting by 1 nodes from <index> to <size - 1>
    @Override
    public void add(int index, E e) throws IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index must be non negative and not greater than current list size (" + size + ")");
        }
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = e;
        size++;
    }


    // Removes first occurrence of list element that equals to <e>. Returns removed value.
    @Override
    public E remove(E e) throws NoSuchElementException {
        int index = indexOf(e);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }


    // Removes list element at index <index> (indexes started from 0). Returns removed value.
    @Override
    public E remove(int index) throws IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index must be non negative and less than current list size (" + size + ")");
        }
        E retValue = (E) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return retValue;
    }


    // Returns list element at index <index> (indexes started from 0).
    @Override
    public E get(int index) throws IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index must be non negative and less than current list size (" + size + ")");
        }
        return (E) array[index];
    }

    // Sets value of list element at index <index> to <element> (indexes started from 0).
    @Override
    public E set(int index, E element) throws IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index must be non negative and less than current list size (" + size + ")");
        }
        array[index] = element;
        return element;
    }


    private void checkCapacity() {
        if (size == capacity) enlargeArray();
    }

    private void enlargeArray() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyArrayList: size = " + size + ", values: {");
        for (int i = 0; i < size - 1; i++) {
            sb.append(array[i].toString());
            sb.append(", ");
        }
        if (size > 0) sb.append(array[size - 1].toString());
        sb.append("}");
        return sb.toString();
    }

}
