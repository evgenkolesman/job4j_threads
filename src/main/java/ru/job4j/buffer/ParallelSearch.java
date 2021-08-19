package ru.job4j.buffer;

import ru.job4j.threadcontrol.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        System.out.println(queue.poll());
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

        ).start();

    }
}