package ru.job4j.multihreadingcourse;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUsage {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());

        for (int i = 0; i < 4; i++) {
            new Sportsman(cyclicBarrier);
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Run extends Thread {
        @Override
        public void run() {
            System.out.println("Run is begin");
        }
    }

    static class Sportsman extends Thread {
        CyclicBarrier cyclicBarrier;

        public Sportsman(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
