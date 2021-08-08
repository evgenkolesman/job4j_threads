package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { });
        Thread second = new Thread(
                () -> { });

        System.out.println(second.getName());
        System.out.println(first.getName());
        while (second.getState() != Thread.State.TERMINATED
                || first.getState() != Thread.State.TERMINATED) {
            second.start();
            first.start();
            System.out.println(second.getState());
            System.out.println(first.getState());
        }
        System.out.println("Thread`s work is complete " + System.lineSeparator()
                + first.getState() + System.lineSeparator()
                + second.getState());
    }
}
