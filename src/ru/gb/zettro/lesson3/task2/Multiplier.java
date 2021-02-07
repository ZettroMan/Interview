package ru.gb.zettro.lesson3.task2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Multiplier {
    private static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        Multiplier multiplier = new Multiplier();

        System.out.println("10 * 20 = " + multiplier.multiply(10, 20));
        System.out.println("100 * 200 = " + multiplier.multiply(100, 200));
        System.out.println("47 * 459 = " + multiplier.multiply(47, 459));
        System.out.println("192 * 311 = " + multiplier.multiply(192, 311));

    }

    public int multiply(int a, int b) throws InterruptedException {
        result = 0;
        CountDownLatch latch = new CountDownLatch(a);
        Lock lock = new ReentrantLock();
        //creating <a> threads (assumes that <a> is not too big)))
        for (int i = 0; i < a; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < b; j++) {
                        lock.lock();
                        result++;
                        lock.unlock();
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await(); // waiting for all <a> threads to be finished
        return result;
    }
}
