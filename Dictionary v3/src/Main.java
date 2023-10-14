import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    DictionaryDBConnection.readData();
    DictionaryCommandLine.dictionaryAdvanced();
  }
}