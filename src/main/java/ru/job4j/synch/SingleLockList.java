package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * Синхронизированный список
 *
 * @param <T>
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy(("this"))
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }

    private Iterable<T> copy(List<T> list) {
        List<T> listIt = list;
        return listIt;
    }
}
