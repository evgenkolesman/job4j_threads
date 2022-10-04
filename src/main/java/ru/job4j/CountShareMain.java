package ru.job4j;
//
//public class CountShareMain {
//    public static void main(String[] args) throws InterruptedException {
//        Count count = new Count();
//        Thread first = new Thread(count::increment);
//        Thread second = new Thread(count::increment);
//        first.start();
//        second.start();
//        first.join();
//        second.join();
//        System.out.println(count.get());
//    }
//}

import org.hamcrest.Matchers;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;


public class CountShareMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            objects.add(i);
        }
        List<Integer> collect = objects.stream().parallel().map(i -> i + 1).collect(Collectors.toList());
        List<Integer> collect1 = objects.parallelStream().map(i -> i + 1).collect(Collectors.toList());
        collect.stream().parallel().forEachOrdered(System.out::print);//bad order because of parallel but can be fixed by forEachOrdered
//        System.out.print(System.lineSeparator());
//        collect.parallelStream().forEach(System.out::print);//bad order because of parallel
//        System.out.print(System.lineSeparator());
//        collect.forEach(System.out::print);
//        System.out.print(System.lineSeparator());
//
//        System.out.println(collect1);
        Set<String> threadName = new HashSet<>();
        objects.parallelStream().forEach(number ->
                {
                    threadName.add(Thread.currentThread().getName());
                    System.out.println(number + " " + Thread.currentThread().getName());
                }
        );


        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        ForkJoinPool customThreadPool = new ForkJoinPool(5);
        int sum = customThreadPool.submit(
                () -> listOfNumbers.parallelStream().reduce(0, Integer::sum)).get();
        customThreadPool.shutdown();
        assertThat(sum, Matchers.equalTo(78));
//        System.out.println(threadName);
//        assertThat("assert of cores",
//                Runtime.getRuntime().availableProcessors(),
//                Matchers.is(threadName.size()));

        System.out.println(Runtime.getRuntime().availableProcessors() + " cores " + threadName.size() + " threads");
    }
}






