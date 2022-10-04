package ru.job4j.cache;


/**
 * Threadsafe cache realization
 * Base - class inserting our cache
 * methods: add, update, delete
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
