package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String dbAddress = "src/main/resources/media/database/dict_hh.db";;
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbAddress);
        }
        return connection;
    }

    public static List<Data> selectFromTable(String table) throws SQLException, ClassNotFoundException {
        List<Data> dataList = new ArrayList<>();
        connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
        while (resultSet.next()) {
            Data data = new Data(resultSet.getInt("id"),
                    resultSet.getString("word"),
                    resultSet.getString("html"),
                    resultSet.getString("description"));
            dataList.add(data);
        }
        return dataList;
    }

    public static List<Data> selectFromTableOrderByWord(String table) throws SQLException, ClassNotFoundException {
        List<Data> dataList = new ArrayList<>();
        connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + " ORDER BY LOWER(word) ASC");
        while (resultSet.next()) {
            Data data = new Data(resultSet.getInt("id"),
                    resultSet.getString("word"),
                    resultSet.getString("html"),
                    resultSet.getString("description"));
            dataList.add(data);
        }
        return dataList;
    }

    public static void insertIntoTable(String table, Data data) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + table + " (word, html, description) VALUES (?, ?, ?)";
        connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, data.getWord());
        preparedStatement.setString(2, data.getHtml());
        preparedStatement.setString(3, data.getDescription());
        preparedStatement.execute();
    }

    public static void insertIntoTable(String table, List<Data> dataList) throws SQLException, ClassNotFoundException {
        for (Data data: dataList) {
            insertIntoTable(table, data);
        }
    }

    public static void updateTable(String table, Data data) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " + table +
                " SET word = ?, html = ?, description = ? " +
                "WHERE id = ?";
        connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, data.getWord());
        preparedStatement.setString(2, data.getHtml());
        preparedStatement.setString(3, data.getDescription());
        preparedStatement.setInt(4, data.getId());
        preparedStatement.execute();
    }

    public static void updateTable(String table, List<Data> dataList) throws SQLException, ClassNotFoundException {
        for (Data data: dataList) {
            updateTable(table, data);
        }
    }

    public static void deleteFromTable(String table, int dataId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + table + " WHERE id = ?";
        connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, dataId);
        preparedStatement.execute();
    }

    public static void deleteFromTable(String table, List<Integer> dataIdList) throws SQLException, ClassNotFoundException {
        for (int dataId: dataIdList) {
            deleteFromTable(table, dataId);
        }
    }

}
