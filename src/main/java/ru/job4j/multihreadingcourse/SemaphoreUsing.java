package ru.job4j.multihreadingcourse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreUsing {

    public static void main(String[] args) {
        //for Semaphore
//        Semaphore semaphore = new Semaphore(2);
//        List<Person> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 5; i++ ) {
//            Person person = new Person();
//            person.table = semaphore;
//            list.add(person);
//        }
//        for (Person person : list) {
//            person.start();
//        }

        //For CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Work(countDownLatch).start();

    }
}


//for semaphore
class Person extends Thread {
    Semaphore table;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " coming and start waiting");
        try {
            table.acquire();
            System.out.println(this.getName() + " beging accting");
            this.sleep(1000);
            System.out.println(this.getName() + " release");
            table.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//for countDownLatch
class Work extends Thread {
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" work is done " + Thread.currentThread().getName());
        countDownLatch.countDown();
    }
}

