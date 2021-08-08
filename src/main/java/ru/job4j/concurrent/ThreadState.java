package ru.job4j.concurrent;

/**
 * Метод показывает состояние нитей в разные периоды
 * производиться вывод данных о названии  и состоянии работы нити
 * Так же идет вывод в консоль о завершении  жизни нитей и их состоянии
 * Пометка при перенесении методов start() внутрь цикла можем получить
 * IllegalThreadStateException
 *
 * @Kolesnikov Evgeniy
 * @version  1.0
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { });
        Thread second = new Thread(
                () -> { });
        second.start();
        first.start();
        System.out.println(second.getName());
        System.out.println(first.getName());
        while (second.getState() != Thread.State.TERMINATED
                || first.getState() != Thread.State.TERMINATED) {
            System.out.println(second.getState());
            System.out.println(first.getState());
        }
        System.out.println("Thread`s work is complete " + System.lineSeparator()
                + first.getState() + System.lineSeparator()
                + second.getState());
    }
}
