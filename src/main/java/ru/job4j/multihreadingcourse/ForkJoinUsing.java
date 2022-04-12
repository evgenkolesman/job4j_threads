package ru.job4j.multihreadingcourse;

import javax.xml.datatype.Duration;
import java.text.SimpleDateFormat;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


public class ForkJoinUsing {

    private static final long numberOperations = 10_000_000_000l;
    static int cores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(cores);
        long start = System.nanoTime();
        pool.invoke(new MyFork(0, numberOperations));
        long end = System.nanoTime();
        System.out.println("time is  " + TimeUnit.NANOSECONDS.toSeconds(end - start));
        countingWithoutAsync();

//        Thread.sleep(5000);


    }

    private static void countingWithoutAsync() {
        long a = 0;
        long start = System.nanoTime();
        for (long i = 0; i < numberOperations; i++) {
            a += i;
        }
        long end = System.nanoTime();

        System.out.println("time is  " + TimeUnit.NANOSECONDS.toSeconds(end - start));
    }

    private static void countWithAsync() {
        long a = 0;
    }

    static class MyFork extends RecursiveTask<Long> {

        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        protected Long compute() {
            if ((to - from) <= numberOperations / cores) {
                long res = 0;
                for (long i = 0; i < to; i++) {
                    res += i;
                }
                return res;
            } else {
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to);
                long secondPart = secondHalf.compute();
                return secondPart + firstHalf.join();
            }
        }
    }
}
