package ru.job4j.multihreadingcourse;

import java.util.concurrent.atomic.AtomicInteger;

public class ClassForAtomicVars {
    static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        for(int z =0; z < 5000; z++) {
            new MyThread().start();
        }
        Thread.sleep(1000);

        System.out.println(i.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            i.incrementAndGet();
//            int k = i+1;
//            i=k;
        }
    }
}
