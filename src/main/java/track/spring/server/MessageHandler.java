package track.spring.server;

import java.io.OutputStream;

/**
 *
 */
//@Component
public class MessageHandler {

    public void handle(String msg, OutputStream out) throws Exception {
        msg = "$$$" + msg + "$$$";
        out.write(msg.getBytes());
        out.flush();
    }

}
