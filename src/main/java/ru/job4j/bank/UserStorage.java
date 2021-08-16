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
 *@author Kolesnikov Evgeniy
 *@version 1.0
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    synchronized boolean add(User user) {
        users.putIfAbsent(user.getId(), user);
        return users.containsValue(user);
    }

    synchronized boolean update(User user) {
        boolean flag = false;
        if (users.containsKey(user.getId())) {
            users.replace(user.getId(), user);
            flag = true;
        }
        return flag;
    }

    synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    synchronized public User findById(int id) {
        return User.of(users.get(id).getId(), users.get(id).getAmount());
    }

    synchronized void transfer(int fromId, int toId, int amount) {
        if (!Objects.equals(users.get(fromId), null)
                && !Objects.equals(users.get(toId), null)) {
            User sourceFrom = findById(fromId);
            User sourceTo = findById(toId);
            if (amount > sourceFrom.getAmount()) {
                System.out.println("It`s not enough money for transfer");
            } else {
                sourceTo.setAmount(sourceTo.getAmount() + amount);
                sourceFrom.setAmount(sourceFrom.getAmount() - amount);
            }
        }
    }
}
