import java.util.Scanner;

public class DictionaryCommandLine {
  public static void showAllWords() {
    System.out.printf("%-4s | %-7s | %s\n", "NO", "English", "Vietnamese");
    int cnt = 1;
    for (String word_expression: Dictionary.getWordList().keySet()) {
      System.out.printf("%-4d | %-7s | %s\n",
          cnt, word_expression, Dictionary.getWordList().get(word_expression).getMeaning());
      cnt++;
    }
  }

  public static void dictionaryBasic() {
    DictionaryManagement.insertFromCommandline();
    showAllWords();
    dictionarySearcher();
  }

  public static void dictionarySearcher() {
    System.out.print("Search: ");
    Scanner sc = new Scanner(System.in);
    String typed = sc.nextLine();
    int typedLength = typed.length();
    for (Word word: Dictionary.getWordList().values()) {
      if (word.getExpression().substring(0, typedLength).equals(typed)) {
        System.out.println(word.getExpression());
      }
    }
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
    System.out.printf("%s", "Your action: ");
  }

  public static void dictionaryAdvanced() {
    boolean running = true;
    while (running) {
      showMenu();
      Scanner input = new Scanner(System.in);
      int userInput = input.nextInt();
      switch (userInput) {
        case 0 -> running = false;
        case 1 -> DictionaryManagement.insertFromCommandline();
        case 2 -> DictionaryManagement.removeWord();
        case 3 -> DictionaryManagement.updateWord();
        case 4 -> showAllWords();
        case 5 -> DictionaryManagement.lookUpWord();
        case 6 -> dictionarySearcher();
        case 7 -> System.out.println("Game still on development");
        case 8 -> DictionaryManagement.insertFromFile();
        case 9 -> DictionaryManagement.exportToFile();
        default -> System.out.println("Action not supported");
      }
    }

  }

}
