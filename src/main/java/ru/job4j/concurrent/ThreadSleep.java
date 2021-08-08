package ru.job4j.concurrent;

public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Start loading ... ");
                for (int i = 1; i < 101; i++) {
                    Thread.sleep(100);
                    System.out.printf("Loaded %d%s...\n", i, "%");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("Main");
    }
}
