package ru.job4j.threadcontrol;

public class Consumer<T> extends Thread {
    private SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public T consume() throws InterruptedException {
        T t = null;
        System.out.println("Consumer object: " + queue.poll());
        t = (T) queue.poll();
        return t;
    }
}
