package ru.geekbrains;

import java.sql.*;

public class SQLHandler {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        stmt = connection.createStatement();
    }

    public static String getNickByLoginPass(String login, String pass) {
        try {
            ResultSet resultSet = stmt.executeQuery(String.format("SELECT nick FROM users WHERE login = '%s' AND pass = '%s';", login, pass));
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
