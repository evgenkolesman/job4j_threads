package ru.job4j.multihreadingcourse;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceUsing {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(new NewThread(), 2, TimeUnit.SECONDS);
        service.shutdown();
    }

    static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread is working");
        }
    }
}
