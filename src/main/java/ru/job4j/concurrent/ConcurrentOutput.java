package ru.job4j.concurrent;

/**
 * Работа по инициализации нити,
 * наглядное тестирование работы нескольких нитей
 *
 * @author Kolesnikov Evgeniy
 * @version  1.0
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
