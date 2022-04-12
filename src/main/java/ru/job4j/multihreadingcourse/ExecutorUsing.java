package ru.job4j.multihreadingcourse;

import java.util.concurrent.*;

public class ExecutorUsing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MyRun());
        Future future = executorService.submit(new MyCall());
        System.out.println(future.get());
        executorService.shutdown(); // обязательно закрывать пулл
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable");
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() {
            return "Callable";
        }
    }
}
