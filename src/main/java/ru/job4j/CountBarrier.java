package ru.job4j;

/**
 * Упарвление нитями с помощью wait
 */
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        int t = 7;
        CountBarrier cb = new CountBarrier(t);
        Thread one = new Thread(
                () -> {

                    for (int i = 0; i < t; i++) {
                        cb.count();
                        System.out.println("one");
                    }
                });
        Thread two = new Thread(
                () -> {
                    System.out.println("two");
                    cb.await();
                });
        one.start();
        two.start();

    }
}
