package UI.PageObject;

import UI.Abstract;

import java.sql.*;

public class SQL extends Abstract {

    public static Connection connection;
    public static Statement statement;
    public ResultSet resultSet;
    public static String URL;
    public static String PASSWORD;
    public static String USERNAME;

    public void Connect () {
        URL = "jbdc:postgresql://111.111.1.11:222/test";
        USERNAME = "qwer";
        PASSWORD = "qwerty";
    }

    /** Метод для поиска значений в БД */
    public void StartConnection(String SQL) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(SQL);
    }

    /** Метод для изменения/создания данных в БД */
    public void UpdateConnection(String SQL) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        statement.executeUpdate(SQL);
    }



































//    public static Connection connection;
//    public static Statement statement;
//    public ResultSet resultSet;
//    public String value;
//    public static String URL;
//    public static String PASSWORD;
//    public static String USERNAME;
//
//    public void Connect() {
//        if (Count == "1") {
//            URL = "jdbc:postgresql://111.111.1.11:222/test";
//            USERNAME = "qwer";
//            PASSWORD = "qwert";
//        }
//        if (Count == "2") {
//            URL = "jdbc:postgresql://111.111.1.11:222/test2";
//            USERNAME = "qwer";
//            PASSWORD = "qwert";
//        }
//    }
//
//    public void StartConnection(String SQL) throws SQLException {
//        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        statement = connection.createStatement();
//        resultSet = statement.executeQuery(SQL);
//    }
//
//    public void UpdateConnection(String SQL) throws SQLException {
//        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        statement = connection.createStatement();
//        statement.executeUpdate(SQL);
//    }
}
