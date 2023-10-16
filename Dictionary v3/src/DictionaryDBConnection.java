import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.plaf.nimbus.State;

public class DictionaryDBConnection {
  public static Connection connection() throws SQLException {
    Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3307/mydictionary",
        "root", "Kien@7124" // change "password" to your database password
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

  /*
   * public static void insertData(String word_expression,
   * String word_meaning) throws SQLException {
   * Connection con = connection();
   * PreparedStatement prepared = con.prepareStatement(
   * "INSERT INTO tbl_edict (word, detail) VALUES "
   * + "('?', '<C><F><I><N><Q>@? /''?/<br />*  ?<br />- ?</Q></N></I></F></C>')");
   * prepared.setString(1, word_expression);
   * prepared.setString(2, word_meaning);
   * prepared.setString(3, word_expression);
   * prepared.execute();
   * }
   * 
   * public static void updateData(String word_expression,
   * String word_meaning) throws SQLException {
   * Connection con = connection();
   * PreparedStatement prepared = con.prepareStatement(
   * "UPDATE tbl_edict SET word = ?, detail = <C><F><I><N><Q>@? "
   * + "/''?/<br />*  ?<br />- ?</Q></N></I></F></C> WHERE word = ?");
   * prepared.setString(1, word_expression);
   * prepared.setString(2, word_meaning);
   * prepared.setString(3, word_expression);
   * prepared.execute();
   * }
   */

}
