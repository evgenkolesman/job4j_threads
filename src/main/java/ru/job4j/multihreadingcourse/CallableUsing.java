package ru.job4j.multihreadingcourse;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableUsing {

    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new MyCall();
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    static class MyCall implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int i = 0;
            for (int j = 0; j < 10; j++, i++) {
                Thread.sleep(500);
            }

            return i;
        }
    }
}
