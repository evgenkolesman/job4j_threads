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

    public void offer(T value) {
    }

    public T poll() {
        return null;
    }
}
