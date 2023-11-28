package Application.Dictionary;

import CommandLine.Dictionary.DictionaryManagement;
import java.sql.*;

public class AppDictionaryManagement extends DictionaryManagement {
    public void importFromDatabase(String database)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM av");
        while (resultSet.next()) {
            addWord(new AppWord(resultSet.getString("word"),
                    resultSet.getString("description"),
                    resultSet.getString("html"),
                    resultSet.getString("pronounce")));
        }
    }

}
