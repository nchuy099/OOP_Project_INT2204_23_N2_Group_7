package application;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.plaf.nimbus.State;

public class DictionaryManagement {

    public static Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/mydictionary",
                "root", "password" // change "password" to your database password
        );
        return connection;
    }

    public static void readData() throws SQLException {
        Connection con = connection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_edict");
        while (resultSet.next()) {
            String word_expression = resultSet.getString("word");
            String word_meaning = resultSet.getString("detail");
            Dictionary.addWord(word_expression, word_meaning);
        }
    }

    public static String lookUpWord(String wordExpression) {
        if (Dictionary.getWordList().containsKey(wordExpression)) {
            Word temp = Dictionary.getWordList().get(wordExpression);
            return temp.getExpression() + "\t" + temp.getMeaning();
        }
        return "null";
    }

}
