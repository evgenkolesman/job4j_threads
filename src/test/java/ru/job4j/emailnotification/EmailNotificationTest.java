package ru.job4j.emailnotification;

import org.junit.jupiter.api.Test;

class EmailNotificationTest {

    @Test
    void emailTo() {
        User user = new User("Alex", "Alex@job.ru");
        EmailNotification notification = new EmailNotification();
        notification.emailTo(user);
        notification.close();
    }
}