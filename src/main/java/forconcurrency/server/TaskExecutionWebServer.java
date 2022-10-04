package forconcurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
    private static final int NUM_THREADS = 100;
    private static final Executor EXEC = Executors.newFixedThreadPool(NUM_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true) {
            final Socket connection = serverSocket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            EXEC.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
