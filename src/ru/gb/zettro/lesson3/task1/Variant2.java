package ru.gb.zettro.lesson3.task1;

import java.util.concurrent.Semaphore;

public class Variant2 {

    private static final Semaphore pingSemaphore = new Semaphore(1);
    private static final Semaphore pongSemaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        pongSemaphore.acquire();

        Thread threadPing = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                    try {
                        pingSemaphore.acquire();
                        System.out.println("PING");
                        pongSemaphore.release();
                    } catch (InterruptedException e) {
                       return;
                    }
            }
        });

        Thread threadPong = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    pongSemaphore.acquire();
                    System.out.println("   pong");
                    pingSemaphore.release();
                } catch (InterruptedException e) {
                   return;
                }
            }
        });

        threadPing.start();
        threadPong.start();

        Thread.sleep(10);
        threadPing.interrupt();
        threadPong.interrupt();

    }
}
