package Database;

import CommandLine.DictionaryManagement;
import CommandLine.Word;

import java.sql.*;

public class Database {
    private static String dictionaryAddress = "src/main/resources/media/database/dict_hh.db";
    private static String bookmarkAddress = "src/main/resources/media/database/dict_hh.db";
    private static DictionaryManagement dictionaryManagement = null;
    private static DictionaryManagement bookmarkManagement = null;

    public static DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public static DictionaryManagement getBookmarkManagement() {
        return bookmarkManagement;
    }

    public static DictionaryManagement importFromDatabase(String database)
            throws SQLException, ClassNotFoundException {
        DictionaryManagement management = new DictionaryManagement();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM av");
        while (resultSet.next()) {
            management.addWord(new Word(resultSet.getString("word"),
                    resultSet.getString("description"),
                    resultSet.getString("html"),
                    resultSet.getString("pronounce")));
        }
        return management;
    }

    public static void initializeDatabase() throws SQLException, ClassNotFoundException {
        dictionaryManagement = importFromDatabase(dictionaryAddress);
        bookmarkManagement = importFromDatabase(bookmarkAddress);
    }

   /* protected DictionaryManagement dictionaryManagement() throws SQLException, ClassNotFoundException {
        DictionaryManagement newManagement = new DictionaryManagement();
        newManagement.importFromDatabase("src/main/resources/media/database/dict_hh.db");
        return newManagement;
    }

    protected DictionaryManagement bookmarkManagement() throws SQLException, ClassNotFoundException {
        DictionaryManagement newManagement = new DictionaryManagement();
        newManagement.insertFromFile("src/main/resources/media/text/bookmark.txt");
        return newManagement;
    }*/
}
