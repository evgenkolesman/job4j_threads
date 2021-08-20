package ru.job4j.atomaric;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int a;
        do {
            a = count.get();
        }
        while (!count.compareAndSet(a, a + 1));
    }

    public int getVal() {
        return count.get();
    }
}