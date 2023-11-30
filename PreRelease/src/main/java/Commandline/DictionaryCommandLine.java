package Commandline;

import Commandline.Game;
import DictionaryP.Dictionary;
import DictionaryP.DictionaryManagement;
import DictionaryP.Search;
import DictionaryP.Word;

import java.sql.SQLException;
import java.util.*;

public class DictionaryCommandLine {
    private static Dictionary dictionary;

    private static Scanner scanner;
    static {
        try {
            dictionary = Search.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner = new Scanner(System.in);
    }

    public static void insertFromCommandline() throws SQLException, ClassNotFoundException {
        scanner.reset();
        System.out.print("Number of Words: ");
        int numberOfWords = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Words:");
        for (int i = 0; i < numberOfWords; i++) {
            Word newWord = new Word();
            System.out.print("Word Expression: ");
            newWord.setExpression(scanner.nextLine());
            System.out.print("Word Meaning: ");
            newWord.setMeaning(scanner.nextLine());
            DictionaryManagement.addWord(newWord, dictionary);
        }
    }

    public static void removeWord() throws SQLException, ClassNotFoundException {
        scanner.reset();
        System.out.print("Remove: ");
        DictionaryManagement.removeWord(scanner.nextLine(), dictionary);
    }

    public static void updateWord() throws SQLException, ClassNotFoundException {
        scanner.reset();
        System.out.print("Update: ");
        String word_expression = scanner.nextLine();
        System.out.print("New Expression: ");
        String newWord_Expression = scanner.nextLine();
        System.out.print("New Meaning: ");
        String newWord_Meaning = scanner.nextLine();
        DictionaryManagement.adjustWord(
                new Word(newWord_Expression, newWord_Meaning), dictionary);
    }

    public static void showAllWords() {
        System.out.printf("%-4s | %-7s | %s\n", "NO", "English", "Vietnamese");
        int cnt = 1;
        for (String word_expression: dictionary.getWordList().keySet()) {
            System.out.printf("%-4d | %-7s | %s\n",
                    cnt, word_expression, dictionary.getWordList()
                            .get(word_expression).getMeaning());
            cnt++;
        }
    }

    public static void lookUpWord() {
        scanner.reset();
        System.out.print("Look up: ");
        String word_expression = scanner.nextLine();
        System.out.println(DictionaryManagement.
                lookUpWord(word_expression, dictionary).getMeaning());
    }

    public static void dictionarySearcher() {
        scanner.reset();
        System.out.print("Search: ");
        String typed = scanner.nextLine();
        int typedLength = typed.length();
        for (Word word: dictionary.getWordList().values()) {
            if (word.getExpression().length() >= typedLength
                    && word.getExpression().substring(0, typedLength).equals(typed)) {
                System.out.println(word.getExpression());
            }
        }
    }

    public static void game() throws SQLException, ClassNotFoundException {
        Game.playGame();
    }

    public static void insertFromFile() throws SQLException {
        scanner.reset();
        System.out.print("Insert from: ");
        String file = scanner.next();
        DictionaryManagement.insertFromFile(file, dictionary);
    }

    public static void exportToFile() {
        scanner.reset();
        System.out.print("Export to: ");
        String file = scanner.next();
        DictionaryManagement.exportToFile(file, dictionary);
    }

    public static void dictionaryApplication() {

    }


    public static void dictionaryBasic() throws SQLException, ClassNotFoundException {
        insertFromCommandline();
        showAllWords();
        dictionarySearcher();
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
        boolean running = true;
        while (running) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            int userscanner = scanner.nextInt();
            switch (userscanner) {
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
