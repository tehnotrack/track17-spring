package track.spring.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Пример инициализации бина через конфигурацию
 * Бин всегда создается как singletone
 */
public class SpringContainerExample {
    static Logger log = LoggerFactory.getLogger(SpringContainerExample.class);

    public static void main(String[] args) throws Exception {

        // Инициализируем контейнер через конфиг
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");


        // Бины можно получать по id и по class
        SocketServer server = (SocketServer)context.getBean("socketServer");


        Thread serverThread = new Thread(server::start);
        serverThread.start();

//        Thread.currentThread().sleep(1000);
//
//        log.info("Server isRunning:" + server.isRunning());
//
//        SocketServer server2 = (SocketServer)context.getBean("socketServer");
//        log.info("Server isRunning:" + server2.isRunning());
//
//        server2.destroy();
//        log.info("Server isRunning:" + server.isRunning());


    }


}
