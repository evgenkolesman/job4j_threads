package ru.job4j.synch;

import org.junit.Test;
import ru.job4j.concurrent.ConcurrentOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest extends ConcurrentOutput {

    @Test
    public void add() throws InterruptedException {
        List<Integer> list1 = new ArrayList<>();
        SingleLockList<Integer> list = new SingleLockList<>(list1);
        Thread first = new Thread(() -> list1.add(1));
        Thread second = new Thread(() -> list1.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }
}