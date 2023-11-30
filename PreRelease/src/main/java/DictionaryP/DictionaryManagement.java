package DictionaryP;

import Database.Data;
import Database.Database;
import DictionaryP.Dictionary;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryManagement {
    public static void addWord(Word newWord, Dictionary dictionary) throws SQLException, ClassNotFoundException {
        newWord.setId(dictionary.getWordList().size() + 50);
        dictionary.getWordList().put(newWord.getExpression(), newWord);
        insertIntoDatabase(dictionary, newWord);
    }

    public static void removeWord(String wordExpression, Dictionary dictionary) throws SQLException, ClassNotFoundException {
        if (dictionary.getWordList().containsKey(wordExpression)) {
            dictionary.getWordList().remove(wordExpression);
            deleteFromDatabase(dictionary, dictionary.getWordList()
                    .get(wordExpression).getId());
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    public static void adjustWord(Word adjustedWord,
                           Dictionary dictionary) throws SQLException, ClassNotFoundException {
        if (dictionary.getWordList().containsKey(adjustedWord.getExpression())) {
            dictionary.getWordList().remove(adjustedWord.getExpression());
            dictionary.getWordList().put(adjustedWord.getExpression(), adjustedWord);
            updateDatabase(dictionary, adjustedWord);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    public static void updateWord(Word newVer, Dictionary dictionary) throws SQLException, ClassNotFoundException {
        if (dictionary.getWordList().containsKey(newVer.getExpression())) {
            Word word = dictionary.getWordList().get(newVer.getExpression());
            word.setMeaning(word.getMeaning() + "\n" + newVer.getMeaning());
            word.setHtml(word.getHtml() + "<hr>" + newVer.getHtml());
            adjustWord(word, dictionary);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    public static Word lookUpWord(String word_expression, Dictionary dictionary) {
        if (dictionary.getWordList().containsKey(word_expression)) {
            return dictionary.getWordList().get(word_expression);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    /** import from file. */
    public static void insertFromFile(String file, Dictionary dictionary) throws SQLException {
        File wordsFile = new File(file);
        try {
            Scanner readFile = new Scanner(wordsFile);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] parts = line.split("|");
                addWord(new Word(parts[0], parts[1]), dictionary);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /** export to file. */
    public static void exportToFile(String file, Dictionary dictionary) {
        try {
            File exportedFile = new File(file);
            // create a new file if file name doesn't exist
            if (!exportedFile.createNewFile()) {
                // if file exist, override
                System.out.println("File already exists. Overriding...");
            }
            FileWriter myWriter = new FileWriter(file);
            for (Word word: dictionary.getWordList().values()) {
                myWriter.write(word.getExpression() + "|" + word.getMeaning() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully export to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred when exporting to file");
        }
    }

    public static void importFromDatabase(Dictionary dictionary) throws SQLException, ClassNotFoundException {
        List<Data> dataList = Database.selectFromTable(dictionary.getDbTable());
        for (Data data: dataList) {
            dictionary.getWordList().put(data.getWord(), new Word(data.getId(),
                    data.getWord(), data.getDescription(), data.getHtml(), data.getPronounce()));
        }
    }

    public static void insertIntoDatabase(Dictionary dictionary, Word word) throws SQLException, ClassNotFoundException {
        Database.insertIntoTable(dictionary.getDbTable(), new Data(word.getId(), word.getExpression(),
                word.getMeaning(), word.getHtml(), word.getIpa()));
    }

    public static void updateDatabase(Dictionary dictionary, Word word) throws SQLException, ClassNotFoundException {
        Database.updateTable(dictionary.getDbTable(), new Data(word.getId(), word.getExpression(),
                word.getMeaning(), word.getHtml(), word.getIpa()));
    }

    public static void deleteFromDatabase(Dictionary dictionary, int wordId) throws SQLException, ClassNotFoundException {
        Database.deleteFromTable(dictionary.getDbTable(), wordId);
    }

}
