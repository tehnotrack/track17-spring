package track.spring.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class SocketServer {
    static Logger log = LoggerFactory.getLogger(SocketServer.class);

//    private int port;
//    private int poolSize;
//    private MessageHandler handler;

    @Value("${port}")
    private int port;

    @Value("${poolSize}")
    private int poolSize;

    @Autowired
    private MessageHandler handler;

    private ExecutorService executor;

    private volatile boolean isRunning;

    public SocketServer() {
//        System.out.println("POST_CONSTRUCT");
//        System.out.println("Init server on port: " + port);
//        System.out.printf("PoolSize: " + poolSize + "\n");
//        executor = Executors.newFixedThreadPool(poolSize);
    }

    @PostConstruct
    public void init() {
        System.out.println("POST_CONSTRUCT");
        System.out.println("Init server on port: " + port);
        System.out.printf("PoolSize: " + poolSize + "\n");
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public MessageHandler getHandler() {
        return handler;
    }

    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }

    public void start() {

        isRunning = true;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Started, waiting for connection");
            while (isRunning) {
                Socket socket = serverSocket.accept();

                System.out.println("Accepted. " + socket.getInetAddress());
                executor.submit(new Worker(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //IoUtil.closeQuietly(serverSocket);
        }
    }

    public void destroy() throws Exception {
        isRunning = false;
        if (executor != null) {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    class Worker extends Thread {

        private Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {

                    byte[] buf = new byte[32 * 1024];
                    int readBytes = in.read(buf);
                    String line = new String(buf, 0, readBytes);
                    System.out.printf("Client>%s\n", line);
                    handler.handle(line, out);
                } catch (Exception e) {
                    log.error(String.format("Error on connection %s:%d", socket.getInetAddress(), socket.getPort()), e);
                    return;
                }
            }
        }
    }
}