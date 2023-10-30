package CommandLine;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryManagement {
    private Dictionary dictionary;

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    /** addWord1. */
    public void addWord(Word newWord) {
        SortedMap<String, Word> tmp = dictionary.getWordList();
        tmp.put(newWord.getExpression(), newWord);
        dictionary.setWordList(tmp);
    }

    /** addWord2. */
    public void addWord(String word_expression, String word_meaning) {
        addWord(new Word(word_meaning, word_meaning));
    }

    /** removeWord1. */
    public void removeWord(String word_expression) {
        if (!dictionary.getWordList().containsKey(word_expression)) {
            SortedMap<String, Word> tmp = dictionary.getWordList();
            tmp.remove(word_expression);
            dictionary.setWordList(tmp);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    /** removeWord2. */
    public void removeWord(Word word) {
        removeWord(word.getExpression());
    }


    /** updateWord1. */
    public void updateWord(String word_expression, Word updatedWord) {
        if (dictionary.getWordList().containsKey(word_expression)) {
            removeWord(word_expression);
            addWord(updatedWord);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    /** updateWord2. */
    public void updateWord(String word_expression, String newWord_Expression,
                           String newWord_Meaning) {
        updateWord(word_expression, new Word(newWord_Expression, newWord_Meaning));
    }

    /** lookUpWord. */
    public Word lookUpWord(String word_expression) {
        if (dictionary.getWordList().containsKey(word_expression)) {
            return dictionary.getWordList().get(word_expression);
        } else {
            throw new NoSuchElementException("Word not found!");
        }
    }

    /** import from file. */
    public void insertFromFile(String file) {
        File wordsFile = new File(file);
        try {
            Scanner readFile = new Scanner(wordsFile);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] parts = line.split("<html>");
                addWord(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /** export to file. */
    public void exportToFile(String file) {
        try {
          File exportedFile = new File(file);
          // create a new file if file name doesn't exist
          if (!exportedFile.createNewFile()) {
            // if file exist, override
            System.out.println("File already exists. Overriding...");
          }
          FileWriter myWriter = new FileWriter(file);
          for (Word word: dictionary.getWordList().values()) {
            myWriter.write(word.getExpression() + "<html>" + word.getMeaning() + "\n");
          }
          myWriter.close();
          System.out.println("Successfully export to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred when exporting to file");
        }
    }

    /** add word 3. */
    public void addWord(String word_expression, String word_meaning,
                        String html, String ipa) {
        addWord(new Word(word_meaning, word_meaning,
                html, ipa));
    }


}
