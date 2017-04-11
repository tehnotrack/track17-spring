package track.lessons.lesson9;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DbManager {
    private String url;
    private String login;
    private String pass;

    private Connection connection;

    public DbManager() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @PostConstruct
    public void init() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + url);
    }

    public Connection getConnection() {
        return connection;
    }
}
