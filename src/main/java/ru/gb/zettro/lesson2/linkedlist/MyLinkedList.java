package ru.gb.zettro.lesson2.linkedlist;


import ru.gb.zettro.lesson2.MyList;

import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {

    private class ListNode {
        E value;
        ListNode next;

        public ListNode(E value) {
            this.value = value;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
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
        head = null;
        tail = null;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }


    // Searches list for an element that equals to <e>.
    // Returns index of found element, otherwise returns -1.
    @Override
    public int indexOf(E e) {
        ListNode currNode = head;
        int index = 0;
        while (currNode != null) {
            if (currNode.value.equals(e)) {
                return index;
            }
            currNode = currNode.next;
            index++;
        }
        return -1;
    }

    @Override
    public void add(E e) {
        addAtTail(e);
    }

    @Override
    public void addAtTail(E e) {
        if (size == 0) {
            head = new ListNode(e);
            tail = head;
            size = 1;
        } else {
            tail.next = new ListNode(e);
            tail = tail.next;
            size++;
        }
    }

    @Override
    public void addAtHead(E e) {
        if (size == 0) {
            addAtTail(e);
        } else {
            ListNode newNode = new ListNode(e);
            newNode.next = head;
            head = newNode;
            size++;
        }
    }


    // Inserts <element> at <index> position, thus shifting by 1 nodes from <index> to <size - 1>
    @Override
    public void add(int index, E e) throws IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index must be non negative and not greater than current list size (" + size + ")");
        }

        if (index == size) {
            addAtTail(e);
            return;
        }
        if (index == 0) {
            addAtHead(e);
            return;
        }
        ListNode prevNode = getNodeByIndex(index - 1);
        ListNode newNode = new ListNode(e);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        size++;
    }


    // Removes first occurrence of list element that equals to <e>. Returns removed value.
    @Override
    public E remove(E e) throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (head.value.equals(e)) {
            E removedValue = head.value;
            head = head.next;
            size--;
            return removedValue;
        }
        return doRemove(findPrevNode(e));
    }

    // Removes list element at index <index> (indexes started from 0). Returns removed value.
    @Override
    public E remove(int index) throws IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index must be non negative and less than current list size (" + size + ")");
        }
        if (index == 0) {
            E removedValue = head.value;
            head = head.next;
            size--;
            return removedValue;
        }
        return doRemove(getNodeByIndex(index - 1));
     }

    private E doRemove(ListNode prevNode) {
        ListNode removedNode = prevNode.next;
        prevNode.next = removedNode.next;
        size--;
        if (prevNode.next == null) {
            tail = prevNode;
        }
        return removedNode.value;
    }


    // Returns list element at index <index> (indexes started from 0).
    @Override
    public E get(int index) throws IllegalArgumentException {
        return getNodeByIndex(index).value;
    }

    // Sets value of list element at index <index> to <element> (indexes started from 0).
    @Override
    public E set(int index, E element) throws IllegalArgumentException {
        getNodeByIndex(index).value = element;
        return element;
    }


    private ListNode getNodeByIndex(int index) throws IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index must be non negative and less than current list size (" + size + ")");
        }
        ListNode currNode = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    // Returns a ListNode prior to node contains searched value <e>
    private ListNode findPrevNode(E e) throws NoSuchElementException {
        ListNode prevNode = null;
        ListNode currNode = head;
        while (currNode != null) {
            if (currNode.value.equals(e)) {
                if (prevNode != null) return prevNode;
                throw new NoSuchElementException();
            }
            prevNode = currNode;
            currNode = currNode.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList: size = " + size + ", values: {");
        ListNode currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.value.toString());
            currentNode = currentNode.next;
            if(currentNode != null) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
