package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { });
        Thread second = new Thread(
                () -> { });

        while (second.getState() != Thread.State.TERMINATED
                || first.getState() != Thread.State.TERMINATED) {
            System.out.println(second.getName());
            System.out.println(first.getName());
        second.start();
        first.start(); }
        System.out.println("Thread`s work is complete " + first.getState()
                + second.getState());
    }
}
