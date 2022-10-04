package ru.job4j.bank;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * It`s threadsafe method
 * can add, update, delete User
 * and can make transfer operation between users
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    // по логике должен быть такой
    synchronized boolean get(int id) {
            return users.get(id) != null;
    }

    synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    synchronized void transfer(int fromId, int toId, int amount) {
        if (!Objects.equals(users.get(fromId), null)
                && !Objects.equals(users.get(toId), null)) {
            User sourceFrom = users.get(fromId);
            User sourceTo = users.get(toId);
            if (amount > sourceFrom.getAmount()) {
                System.out.println("It`s not enough money for transfer");
            } else {
                sourceTo.setAmount(sourceTo.getAmount() + amount);
                sourceFrom.setAmount(sourceFrom.getAmount() - amount);
            }
        }
    }
}
