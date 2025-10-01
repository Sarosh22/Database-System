package com.hr.dao;
import java.sql.*;

public class MyDatabaseDAO {

    private static String jdbcUrl;
    private static String username;
    private static String password;

    public MyDatabaseDAO(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public static void fetchData() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT column1, column2 FROM your_table";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                // Get metadata to determine the number of columns
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println("Column " + i + ": " + resultSet.getString(i));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hr";
        String password = "oracle";

        MyDatabaseDAO dao = new MyDatabaseDAO(jdbcUrl, username, password);
        dao.fetchData();
    }
}
