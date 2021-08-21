package ru.job4j.pool;

import ru.job4j.threadcontrol.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * ThreadPool example
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    tasks.poll().run();
                }
            });
            threads.add(thread);
            thread.start();
        }

    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
