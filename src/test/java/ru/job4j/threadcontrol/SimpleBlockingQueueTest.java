package ru.job4j.threadcontrol;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void testQueue() throws InterruptedException {
        SimpleBlockingQueue q = new SimpleBlockingQueue(5);
        Thread producer = new Thread(new Producer(q));
        Thread consumer = new Thread(new Consumer(q));
        consumer.start();
        producer.start();
        producer.join(10000);
        consumer.join(10000);
    }

}