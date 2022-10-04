package ru.job4j.ref;

import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Thread safe
 */
@ThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName())); // здесь надо добавить копию чтобы избежать ошибок
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        return users.values().stream()
                .map(a -> User.of(a.getName())) // аналогично как в прошлом методе делаем вызов копий и мапим их
                .collect(Collectors.toList()); // копия листа, что бы не было задвоений
    }
}
