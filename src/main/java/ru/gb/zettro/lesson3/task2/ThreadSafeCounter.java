package ru.gb.zettro.lesson3.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCounter {
    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public int increment() {
        try {
            lock.lock();
            counter++;
            return counter;
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}
