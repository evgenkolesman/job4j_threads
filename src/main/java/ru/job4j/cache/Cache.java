package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Threadsafe cache realization
 * methods: add, update, delete
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            if (model.getVersion() != memory.get(model.getId()).getVersion()) {
                throw new OptimisticException("No value in cache");
            }
            return  new Base(model.getId(), model.getVersion() + 1);
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
