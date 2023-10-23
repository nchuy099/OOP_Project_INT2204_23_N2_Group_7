package Bone;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    DictionaryCommandLine.initSystemDictionaryManagement();
    DictionaryCommandLine.dictionaryAdvanced();
  }
}