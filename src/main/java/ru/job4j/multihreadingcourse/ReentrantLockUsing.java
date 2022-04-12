package ru.job4j.multihreadingcourse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUsing {

    public static void main(String[] args) throws Exception {
        Resource resource = new Resource();
        resource.i = 5;
        resource.k = 5;
        resource.s = 5;

        MyThread thread1 = new MyThread();
        thread1.setName("one");
        MyThread thread2 = new MyThread();
        thread1.resource = resource;
        thread2.resource = resource;
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(resource.i + " " + resource.k + " " + resource.s);

    }

    static class MyThread extends Thread {
        Resource resource;
        @Override
        public void run() {
            resource.changeI();
//            resource.changeK();
        }
    }



}

class Resource {
    int i;
    int k;
    int s;

    // may use synchronized or Locks

    Lock lock = new ReentrantLock();

    void changeI() {
        lock.lock();
        int i= this.i;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        i++;
        this.i = i;
        changeK();
        changeS();
    }

    void changeK() {
        int k= this.k;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        k++;
        this.k = k;
    }

    void changeS() {

        int s= this.s;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        s++;
        this.s = s;
        lock.unlock();
    }



}
