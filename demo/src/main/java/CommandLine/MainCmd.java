package CommandLine;

import CommandLine.Dictionary.DictionaryCommandLine;

import java.sql.SQLException;

public class MainCmd {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    DictionaryCommandLine.dictionaryAdvanced();
  }
}