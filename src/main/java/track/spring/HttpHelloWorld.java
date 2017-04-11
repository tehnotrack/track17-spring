package track.spring;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Отдает html браузеру
 *
 * Browser:
 *   localhost:8081
 *   localhost:8081?mode=image
 */
public class HttpHelloWorld extends AbstractHandler {

    public static final int PORT = 8082;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);


        // Можем посмотреть параметры запроса
        String mode = request.getParameter("mode");

        System.out.println("test");
        System.out.println("x=" + request.getParameter("x"));
        System.out.println("y=" + request.getParameter("y"));
        baseRequest.setHandled(true);
//
//        if ("image".equals(mode)) {
//            File imageFile = new File(this.getClass().getResource("/images/service.png").getFile());
//            response.setHeader("Content-Type", "image/png");
//            response.setHeader("Content-Length", String.valueOf(imageFile.length()));
//            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFile.getName() + "\"");
//
//            OutputStream out = response.getOutputStream();
//
//            InputStream in = new FileInputStream(imageFile);
//            // Copy the contents of the file to the output stream
//            byte[] buf = new byte[1024];
//            int count = 0;
//            while ((count = in.read(buf)) >= 0) {
//                out.write(buf, 0, count);
//            }
//        } else {
//            String html = "<p><span style=\"color: red; font-size: 5em\">L</span>orem ipsum dolor \n" +
//                    "  sit amet, consectetuer adipiscing elit, sed diem nonummy nibh euismod tincidunt \n" +
//                    "  ut lacreet dolore magna aliguam erat volutpat.</p>";
//            response.getWriter().println(html);
//        }
    }

    public static void main(String[] args) throws Exception {

        // Server из библиотеки jetty
        Server server = new Server(PORT);

        // Обработчик соединения - наш класс
        server.setHandler(new HttpHelloWorld());

        server.start();
        server.join();
    }
}