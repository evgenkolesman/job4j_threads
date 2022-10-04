package ru.job4j.threadcontrol;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
                wait();
        }
        notifyAll();
        queue.add(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
                wait();
        }
        notifyAll();
        return queue.remove();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
