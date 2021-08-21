package ru.job4j.emailnotification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        executorService.submit(new Thread(() -> {
            String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.email);
            System.out.println(subject);
            System.out.println(body);
        }));
    }

    public void close() {
        executorService.shutdown();
    }

    public void send(String subject, String body, String email) {
    }
}
