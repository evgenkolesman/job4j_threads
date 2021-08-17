package ru.job4j.bank;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    void updateTest() {
        User user = new User(1, 1000);
        user.setAmount(2000);
        UserStorage storage = new UserStorage();
        storage.add(user);
        assertThat(storage.update(user), is(user));
    }

    @Test
    void deleteTest() {
        User user = new User(1, 1000);
        UserStorage storage = new UserStorage();
        storage.add(user);
        assertThat(storage.delete(user), is(true));
    }

    @Test
    void transferTest() {
        User user = new User(1, 1000);
        User user1 = new User(2, 2000);
        UserStorage storage = new UserStorage();
        storage.add(user);
        storage.add(user1);
        storage.transfer(user1.getId(), user.getId(), 1000);
        assertThat(user1.getAmount(), is(1000));
        assertThat(user.getAmount(), is(2000));
    }
}