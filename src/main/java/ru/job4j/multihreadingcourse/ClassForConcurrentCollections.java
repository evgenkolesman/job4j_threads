package ru.job4j.multihreadingcourse;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClassForConcurrentCollections {

    static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();

    public static void main(String[] args) throws Exception {
//        list.addAll(List.of(1,2,3,4,5));
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                list.forEach(System.out :: println);
//            }
//        };
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                list.forEach(System.out :: println);
//            }
//        };
//
//        thread1.start();
//        thread2.start();
//        thread1.join();
       NameList  list = new NameList();
       list.add("First");
       Thread thread1 = new Thread() {
           @Override
           public void run() {
               System.out.println(list.removeFirst());
           }
       };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println(list.removeFirst());
            }
        };
       thread1.setName("one");
        thread1.start();
        thread2.start();

//        class MyThread extends Thread {
//            @Override
//            public void run() {
//                System.out.println(list.removeFirst());
//            }
//        }
//        new MyThread().start();
//        new MyThread().start();


    }

    static class NameList {
        private List list = Collections.synchronizedList(new ArrayList<>());


        public void add(String value) {
            list.add(value);
        }

        public synchronized String removeFirst() {
            if (list.size() > 0) {
                if (Thread.currentThread().getName().equals("one")) {
                    Thread.yield();
                }
                return (String) list.remove(0);
            }
            return "List is empty";
        }
    }
}
