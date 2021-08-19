package ru.job4j.threadcontrol;

import java.util.Random;

public class Producer implements Runnable {
    private SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            int a = valueRandom();
            queue.offer(a);

        }
    }

    public int valueRandom() {
        Random random = new Random();
        int i = random.nextInt();
        System.out.println("Producer object: " + i);
        return i;
    }
}
