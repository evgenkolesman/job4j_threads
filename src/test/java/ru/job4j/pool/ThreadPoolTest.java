package ru.job4j.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for the Threadpool.java
 * when threads number in size, and when over
 */
public class ThreadPoolTest {
    private final int size = Runtime.getRuntime().availableProcessors();

    @Test
    public void workTest() {
        List<Runnable> list = new ArrayList<>();
        List<Integer> listCount = new ArrayList<>();
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < size; i++) {
            Runnable job = new Runnable() {
                int count;

                @Override
                public void run() {
                    count++;
                    listCount.add(count);

                }
            };
            list.add(job);
        }

        for (Runnable job : list) {
            try {
                threadPool.work(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
//        assertThat(list.size(), is(12)); // это число может быть любым просто выявил количество потоков которое может работать одновременно
//        assertThat(listCount.get(listCount.size() - 1), is(1));
    }


    @Test
    public void whenMoreThanSize() {
        Set<String> set = new LinkedHashSet<>();
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 100; i++) {
            try {
                threadPool.work(() -> {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "Working");
                    set.add(name);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        //assertThat(set.size(), is(12));
    }

}