package ru.gb.zettro.lesson3.task1;

import static java.lang.Thread.sleep;

public class Main {

    private static final Object mon = new Object();
    private static final boolean PING_TURN = true;
    private static final boolean PONG_TURN = false;
    private static boolean turn = PING_TURN;

    public static void main(String[] args) throws InterruptedException {
        Thread threadPing = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (mon) {
                    if (turn == PING_TURN) {
                        System.out.println("PING");
                        turn = PONG_TURN;
                    }
                }
            }
        });

        Thread threadPong = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (mon) {
                    if (turn == PONG_TURN) {
                        System.out.println("   pong");
                        turn = PING_TURN;
                    }
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
