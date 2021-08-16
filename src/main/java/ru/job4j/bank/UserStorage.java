package ru.job4j.bank;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    boolean add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getId(), user.getAmount()));
        return users.containsValue(user);
    }

    boolean update(User user) {
        boolean flag = false;
        User userUpdate = users.get(user.getId());
        if (userUpdate != null) {
            users.put(user.getId(), user);
            flag = true;
        }
        return flag;
    }

    boolean delete(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        } else {
            return false;
        }
    }

    public User findById(int id) {
        return User.of(users.get(id).getId(), users.get(id).getAmount());
    }

    void transfer(int fromId, int toId, int amount) {
        User sourceFrom = findById(fromId);
        User sourceTo = findById(toId);
        sourceTo.setAmount(sourceTo.getAmount() + amount);
        sourceFrom.setAmount(sourceFrom.getAmount() - amount);
    }


}
