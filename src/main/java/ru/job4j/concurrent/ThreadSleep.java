package ru.job4j.concurrent;

/**
 * модулируем простую загрузку любой программы
 * вывод процентов в одном месте в консоли
 *
 * @author Kolesnikov Evgeniy
 * version 1.0
 */
public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Start loading ... ");
                for (int i = 1; i < 101; i++) {
                    Thread.sleep(100);
                    System.out.printf("Loaded %d%s...\r", i, "%");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
