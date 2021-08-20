package ru.job4j.atomaric;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int a;
        do {
            a = count.get();
        } while (a != count.compareAndExchange(a, a + 1));

    }

    public int getVal() {
        return count.get();
    }
}