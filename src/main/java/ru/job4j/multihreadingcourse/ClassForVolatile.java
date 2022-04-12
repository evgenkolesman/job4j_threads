package ru.job4j.multihreadingcourse;

public class ClassForVolatile {
     static int i;

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (i < 5) {
                    System.out.println(" Result " + ++i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int local = i;
                while (i < 5) {
                   if (local != i ) {
                       System.out.println( " New value of i  " + i);
                       local = i;
                   }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread2.join();
        thread1.join();
//        System.out.println(i);
    }
}

