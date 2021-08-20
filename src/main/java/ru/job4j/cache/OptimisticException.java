package ru.job4j.cache;

/**
 * Threadsafe cache realization
 * Exception when wrong version in Base class object
 * methods: add, update, delete
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}
