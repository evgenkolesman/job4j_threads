package ru.job4j.cache;

import java.util.Map;
import java.util.Objects;
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
        if(!Objects.equals(model.getVersion(), memory.get(model.getId()).getVersion())) {
            throw new OptimisticException("No value in cache");
        } else {
            Base modelUpdate = new Base(model.getId(), model.getVersion() + 1);
            return memory.replace(modelUpdate.getId(), modelUpdate) != null;
        }
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
