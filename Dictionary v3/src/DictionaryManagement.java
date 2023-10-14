import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryManagement {
  /** add words. */
  public static void insertFromCommandline() {
    // set amount of inserted word
    System.out.print("Number of words: ");
    Scanner sc = new Scanner(System.in);
    int amountInserted = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < amountInserted; i++) {
      // construct new word
      System.out.println("Word " + (i + 1));
      Word newWord = new Word();
      System.out.print("English expression: ");
      newWord.setExpression(sc.nextLine());
      System.out.print("Vietnamese meaning: ");
      newWord.setMeaning(sc.nextLine());
      // add new word into dictionary
      Dictionary.addWord(newWord);
    }
  }

  /** import from file. */
  public static void insertFromFile() {
    // enter file name to be imported
    System.out.print("File name: ");
    Scanner input = new Scanner(System.in);
    String fileName = input.next();
    // scan file
    File wordsFile = new File(fileName + ".txt");
    Scanner readFile = null;
    try {
      readFile = new Scanner(wordsFile);
      System.out.println("Successfully import from file.");
    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }
    // import data to dictionary
    while (readFile != null && readFile.hasNextLine()) {
      String line = readFile.nextLine();
      String[] parts = line.split("\t");
      Word newWord = new Word();
      newWord.setExpression(parts[0]);
      newWord.setMeaning(parts[1]);
      Dictionary.addWord(newWord);
    }
  }

  /** export to file. */
  public static void exportToFile() {
    // enter file name to be exported
    Scanner input = new Scanner(System.in);
    System.out.print("File name: ");
    String fileName = input.next();
    try {
      File exportedFile = new File(fileName + ".txt");
      // create a new file if file name doesn't exist
      if (exportedFile.createNewFile()) {
        System.out.println("File created: " + exportedFile.getName());
      } else {
        // if file exist, override
        System.out.println("File already exists. Overrode!");
      }
      FileWriter myWriter = new FileWriter(fileName + ".txt");
      for (Word word: Dictionary.getWordList().values()) {
        myWriter.write(word.getExpression() + "\t" + word.getMeaning() + "\n");
      }
      myWriter.close();
      System.out.println("Successfully export to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred when exporting to file");
    }
  }

  /** lookUp Word. */
  public static void lookUpWord() {
    System.out.print("Look Up: ");
    Scanner input = new Scanner(System.in);
    String word_expression = input.nextLine();
    if (Dictionary.getWordList().containsKey(word_expression)) {
      Dictionary.getWordList().get(word_expression).showWord();
    } else {
      System.out.println("101 Error: Word not found :(");
    }
  }

  public static void addWord(String word_expression, String word_meaning) {
    Dictionary.addWord(word_expression, word_meaning);
  }

  /** remove Word. */
  public static void removeWord() {
    System.out.print("Remove: ");
    Scanner input = new Scanner(System.in);
    String word_expression = input.nextLine();
    if (Dictionary.getWordList().containsKey(word_expression)) {
      Dictionary.getWordList().remove(word_expression);
      System.out.println("Mission completed!");
    } else {
      System.out.println("404 Error: Word not found :(");
    }
  }

  /** update word. */
  public static void updateWord() {
    Scanner input = new Scanner(System.in);
    System.out.print("Update: ");
    String curExpression = input.nextLine();
    if (Dictionary.getWordList().containsKey(curExpression)) {
      System.out.print("New Expression: ");
      String newExpression = input.nextLine();
      System.out.print("New Meaning: ");
      String newMeaning = input.nextLine();
      Dictionary.getWordList().get(curExpression).setExpression(newExpression);
      Dictionary.getWordList().get(curExpression).setMeaning(newMeaning);
      System.out.println("Mission completed!");
    } else {
      System.out.println("404 Error: Word Not Found :(");
    }
  }


}
