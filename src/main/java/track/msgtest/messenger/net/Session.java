package track.msgtest.messenger.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import track.msgtest.messenger.User;
import track.msgtest.messenger.messages.Message;


/**
 * Сессия связывает бизнес-логику и сетевую часть.
 * Бизнес логика представлена объектом юзера - владельца сессии.
 * Сетевая часть привязывает нас к определнному соединению по сети (от клиента)
 */
public class Session implements Runnable {
    static Logger log = LoggerFactory.getLogger(Session.class);

    private User user;
    private Socket socket;
    private Protocol protocol;

    private InputStream in;
    private OutputStream out;

    private volatile boolean isActive;
    private byte[] buffer = new byte[1024 * 16]; // 16 kb

    public Session(User user, Socket socket, Protocol protocol) throws IOException {
        this.user = user;
        this.socket = socket;
        this.protocol = protocol;

        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void send(Message msg) throws ProtocolException, IOException {
        // TODO: Отправить клиенту сообщение
    }

    public void onMessage(Message msg) {
        // TODO: Пришло некое сообщение от клиента, его нужно обработать
    }

    public void close() {
        // TODO: закрыть in/out каналы и сокет. Освободить другие ресурсы, если необходимо
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                int readBytes = in.read(buffer);
                final byte[] slice = Arrays.copyOfRange(buffer, 0, readBytes);

            } catch (Exception e) {
                isActive = false;
                log.error("Session failed: ", e);
            }
        }
    }
}