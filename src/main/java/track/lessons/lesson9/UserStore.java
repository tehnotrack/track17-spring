package track.lessons.lesson9;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.istack.internal.NotNull;

/**
 *
 */
public class UserStore {
    static Logger log = LoggerFactory.getLogger(UserStore.class);

    private DbManager dbManager;

    public UserStore() {
    }

    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @NotNull
    public List<User> getUsers(int limit) throws SQLException {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users LIMIT " + limit + ";";
        try (Statement stmt = dbManager.getConnection().createStatement()) {
            ResultSet rs = null;
            try {
                log.trace("getUsers(): " + query);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setAge(rs.getInt("age"));
                    users.add(user);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        }
        return users;
    }

    public List<User> getUsers2(int limit) throws SQLException {

        String query = "SELECT * FROM users LIMIT " + limit + ";";
        return QueryExecutor.execQuery(dbManager.getConnection(), query, rs -> {
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                userList.add(user);
            }
            return userList;
        });
    }

    public void updateUser(long id, User user) {
        String url = "UPDATE users SET name=?, age=? WHERE id=?";
        PreparedStatement stmt = null;
        try {

            stmt = dbManager.getConnection().prepareStatement(url);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setLong(3, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows);

        } catch (SQLException e) {

        }
    }
}
