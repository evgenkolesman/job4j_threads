package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * This class reads data from
 *
 * @version 1.0
 * @url = args[0]
 * with speed
 * @speed = args[1]
 * and writes it in the result file
 * path is in run method may be changed in different ways
 * we may use not class either record
 * @Kolesnikov Evgeniy
 */
public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static void main(String[] args) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String url = args[0];
                int speed = Integer.parseInt(args[1]);
                Thread wget = new Thread(new Wget(url, speed));
                //wget.start();
                wget.run();
                wget.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/ru/job4j/concurrent/result.html")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (speed > 1024) {
                    Thread.sleep(15);
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}