package track.lessons.lesson9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import track.util.Util;

public class JdbcExample {

    static Logger log = LoggerFactory.getLogger(JdbcExample.class);

    public static final String PATH_TO_DB = "/Users/dmirty/Dima/track17-spring/track17-spring/track.sqlite";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Получение соединения с базой.

        // 1) Загружаем драйвер в наше приложение через ClassLoader
//        Class.forName("org.sqlite.JDBC");

        // 2) База идентифицируется урлом, в случае SQLite - это путь к файлу в ФС
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB);

        final String sql = "SELECT * FROM users LIMIT 3;";

        Statement stmt = null;
        ResultSet rs = null;
        try {

            // 3) Это объект для передачи запроса в базу
            stmt = connection.createStatement();

            // 4) Набор "строк" таблицы - результат SELECT
            rs = stmt.executeQuery(sql);

            // 5) Структура ResultSet - получаем строки, пока есть
            while (rs.next()) {
                // Column index starts with 1
                Integer id = rs.getInt(1);          // 1 - ID
                String name = rs.getString("name"); // 2 - name
                Integer age = rs.getInt("age");     // 3 - age

                System.out.println(String.format("ID: %d, name: %s, age: %d", id, name, age));

            }
        } catch (SQLException e) {
            log.error("Failed to execute statement: " + sql, e);
        } finally {
            Util.closeQuietly(rs, stmt);
        }


//
//        stmt = connection.createStatement();
//        sql = "CREATE TABLE IF NOT EXISTS company" +
//                "(ID INT PRIMARY KEY     NOT NULL," +
//                " NAME           TEXT    NOT NULL, " +
//                " AGE            INT     NOT NULL, " +
//                " ADDRESS        CHAR(50), " +
//                " SALARY         REAL)";
//        stmt.executeUpdate(sql);
//        stmt.close();
//        c.close();
//
//        connection.setAutoCommit(false);
//
//        stmt = connection.createStatement();
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
//        stmt.executeUpdate(sql);
//
//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
//        stmt.executeUpdate(sql);
//
//        stmt.close();
//        connection.commit();
////        c.close();
//
//        stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            int age = rs.getInt("age");
//            String address = rs.getString("address");
//            float salary = rs.getFloat("salary");
//            System.out.println("ID = " + id);
//            System.out.println("NAME = " + name);
//            System.out.println("AGE = " + age);
//            System.out.println("ADDRESS = " + address);
//            System.out.println("SALARY = " + salary);
//            System.out.println();
//        }
//        rs.close();
//        stmt.close();
////        c.close();
//
//        stmt = connection.createStatement();
//        sql = "UPDATE COMPANY SET SALARY = 25000.00 WHERE ID=1;";
//        stmt.executeUpdate(sql);
//        connection.commit();
//
//        rs = stmt.executeQuery("SELECT * FROM COMPANY;");
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            int age = rs.getInt("age");
//            String address = rs.getString("address");
//            float salary = rs.getFloat("salary");
//            System.out.println("ID = " + id);
//            System.out.println("NAME = " + name);
//            System.out.println("AGE = " + age);
//            System.out.println("ADDRESS = " + address);
//            System.out.println("SALARY = " + salary);
//            System.out.println();
//        }
//        rs.close();
//        stmt.close();
////        c.close();
//
//        stmt = connection.createStatement();
//        sql = "DELETE FROM COMPANY WHERE ID=2;";
//        stmt.executeUpdate(sql);
//        connection.commit();
//
//        rs = stmt.executeQuery("SELECT * FROM COMPANY;");
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            int age = rs.getInt("age");
//            String address = rs.getString("address");
//            float salary = rs.getFloat("salary");
//            System.out.println("ID = " + id);
//            System.out.println("NAME = " + name);
//            System.out.println("AGE = " + age);
//            System.out.println("ADDRESS = " + address);
//            System.out.println("SALARY = " + salary);
//            System.out.println();
//        }
//        rs.close();
//        stmt.close();


/*
        PreparedStatement prepStmnt = connection.prepareStatement("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );");
        int parameterIndex = 2;
        prepStmnt.setString(parameterIndex, "asd");
        rs = prepStmnt.executeQuery();


        *//**
         * Использование executor для запроса в базу
         *//*
        QueryExecutor exec = new QueryExecutor();
        List<User> users = exec.execQuery(connection, "SELECT * FROM users;", (rset) -> {
            System.out.println("handle:");
            List<User> data = new ArrayList<>();
            while (rset.next()) {
                User user = new User();
                user.setName(rset.getString(2));
                data.add(user);
            }
            return data;
        });

        System.out.println(users.toString());


        *//**
         * Использование prepared executor для запроса в базу
         *//*
        Map<Integer, Object> prepared = new HashMap<>();
        prepared.put(1, "John");

        users = exec.execQuery(connection, "SELECT * FROM users WHERE name = ?;", prepared, (rset) -> {
            System.out.println("handle:");
            List<User> data = new ArrayList<>();
            while (rset.next()) {
                User user = new User();
                user.setName(rset.getString(2));
                data.add(user);
            }
            return data;
        });

        System.out.println(users.toString());*/

        connection.close();
    }
}
