package track.lessons.lesson9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class QueryExecutor {
    // Простой запрос
    public static <T> T execQuery(Connection connection, String query, ResultHandler<T> handler) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

    // Подготовленный запрос
    public static <T> T execQuery(Connection connection, String query, Map<Integer, Object> args, ResultHandler<T> handler) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (Map.Entry<Integer, Object> entry : args.entrySet()) {
            stmt.setObject(entry.getKey(), entry.getValue());
        }
        ResultSet rs = stmt.executeQuery();
        T value = handler.handle(rs);
        rs.close();
        stmt.close();
        return value;
    }

    // Также нужно реализовать Update запросы

    public static void main(String[] args) throws Exception {
        new QueryExecutor().execQuery(null, "Select from user", new ResultHandler<Object>() {
            @Override
            public Object handle(ResultSet resultSet) throws SQLException {
                User user = new User();
                String name = resultSet.getString(0);

                return user;
            }
        });
    }
}
