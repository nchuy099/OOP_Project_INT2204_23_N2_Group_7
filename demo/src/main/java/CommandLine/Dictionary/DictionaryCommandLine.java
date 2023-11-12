package CommandLine.Dictionary;

import Application.Dictionary.Search;
import Application.MainApplication;
import CommandLine.Game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.sql.SQLException;
import java.util.*;

public class DictionaryCommandLine {
  private static DictionaryManagement cmdDictionaryManagement = null;
  private static Scanner input = new Scanner(System.in);

  public static void insertFromCommandline() {
    input.reset();
    System.out.print("Number of Words: ");
    int numberOfWords = input.nextInt();
    input.nextLine();
    System.out.println("Enter Words:");
    for (int i = 0; i < numberOfWords; i++) {
      Word newWord = new Word();
      System.out.print("Word Expression: ");
      newWord.setExpression(input.nextLine());
      System.out.print("Word Meaning: ");
      newWord.setMeaning(input.nextLine());
      cmdDictionaryManagement.addWord(newWord);
    }
  }

  public static void removeWord() {
    input.reset();
    System.out.print("Remove: ");
    cmdDictionaryManagement.removeWord(input.nextLine());
  }

  public static void updateWord() {
    input.reset();
    System.out.print("Update: ");
    String word_expression = input.nextLine();
    System.out.print("New Expression: ");
    String newWord_Expression = input.nextLine();
    System.out.print("New Meaning: ");
    String newWord_Meaning = input.nextLine();
    cmdDictionaryManagement.updateWord(word_expression,
            newWord_Expression, newWord_Meaning);
  }

  public static void showAllWords() {
    System.out.printf("%-4s | %-7s | %s\n", "NO", "English", "Vietnamese");
    int cnt = 1;
    for (String word_expression: cmdDictionaryManagement.getDictionary().getWordList().keySet()) {
      System.out.printf("%-4d | %-7s | %s\n",
              cnt, word_expression, cmdDictionaryManagement.getDictionary()
                      .getWordList().get(word_expression).getMeaning());
      cnt++;
    }
  }

  public static void lookUpWord() {
    input.reset();
    System.out.print("Look up: ");
    String word_expression = input.nextLine();
    System.out.println(cmdDictionaryManagement.lookUpWord(word_expression).getMeaning());
  }

  public static void dictionarySearcher() {
    input.reset();
    System.out.print("Search: ");
    String typed = input.nextLine();
    int typedLength = typed.length();
    for (Word word: cmdDictionaryManagement.getDictionary().getWordList().values()) {
      if (word.getExpression().length() >= typedLength
              && word.getExpression().substring(0, typedLength).equals(typed)) {
        System.out.println(word.getExpression());
      }
    }
  }

  public static void game() throws SQLException, ClassNotFoundException {
    Game.playGame();
  }

  public static void insertFromFile() {
    input.reset();
    System.out.print("Insert from: ");
    String file = input.next();
    cmdDictionaryManagement.insertFromFile(file);
  }

  public static void exportToFile() {
    input.reset();
    System.out.print("Export to: ");
    String file = input.next();
    cmdDictionaryManagement.exportToFile(file);
  }

  public static void dictionaryApplication() {

  }


  public static void dictionaryBasic() {
    insertFromCommandline();
    showAllWords();
    dictionarySearcher();
  }

  public static void initcmdDictionaryManagement() throws SQLException, ClassNotFoundException {
    cmdDictionaryManagement = Search.getInstance();
  }

  public static void showMenu() {
    System.out.println("[0] Exit");
    System.out.println("[1] Add");
    System.out.println("[2] Remove");
    System.out.println("[3] Update");
    System.out.println("[4] Display");
    System.out.println("[5] Lookup");
    System.out.println("[6] Search");
    System.out.println("[7] Game");
    System.out.println("[8] Import from file");
    System.out.println("[9] Export to file");
    //System.out.println("[10] Application");
    System.out.printf("%s", "Your action: ");
  }

  public static void dictionaryAdvanced() throws SQLException, ClassNotFoundException {
    initcmdDictionaryManagement();
    boolean running = true;
    while (running) {
      showMenu();
      Scanner input = new Scanner(System.in);
      int userInput = input.nextInt();
      switch (userInput) {
        case 0 -> running = false;
        case 1 -> insertFromCommandline();
        case 2 -> removeWord();
        case 3 -> updateWord();
        case 4 -> showAllWords();
        case 5 -> lookUpWord();
        case 6 -> dictionarySearcher();
        case 7 -> game();
        case 8 -> insertFromFile();
        case 9 -> exportToFile();
        //case 10 -> MainApplication;
        default -> System.out.println("Action not supported");
      }
    }
  }

}
