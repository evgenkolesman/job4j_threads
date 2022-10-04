package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(2000); /* симулируем выполнение параллельной задачи в течение 1 секунды.*/
        progress.interrupt();
    }

    @Override
    public void run() {
        String[] process = {"-", "\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (String c : process) {
                    System.out.print("\r load: " + c);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace(); для задания 3.1
                Thread.currentThread().interrupt();
            }
        }
    }
}

