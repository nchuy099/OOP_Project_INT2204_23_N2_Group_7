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

    public static void addWordByOrder(Word newWord, Dictionary dictionary) throws SQLException, ClassNotFoundException {
        insertIntoDatabase(dictionary, newWord);
        int index;
        if (checkInDict(newWord.getExpression(), dictionary)) {
            index = getLastIndex(newWord.getExpression(), dictionary) + 1;
        }
        else {
            index = getIndex(newWord.getExpression(), dictionary);
        }
        // last index if exist in dict, recommending index if not
        dictionary.getWordList().add(index, newWord);
    }

    public static void removeWord(int wordId, String wordExpression, Dictionary dictionary) throws SQLException, ClassNotFoundException {
        int index = searchIndexById(wordId, wordExpression, dictionary);
        dictionary.getWordList().remove(index);
        deleteFromDatabase(dictionary, wordId);
    }

    public static void updateWord(String oldExpression, Word updatedWord,
                                  Dictionary dictionary) throws SQLException, ClassNotFoundException {
        if (oldExpression.equalsIgnoreCase(updatedWord.getExpression())) {
            int index = searchIndexById(updatedWord.getId(), oldExpression, dictionary);
            updateDatabase(dictionary, updatedWord);
            dictionary.getWordList().set(index, updatedWord);
        } else {
            removeWord(updatedWord.getId(), oldExpression, dictionary);
            addWordByOrder(updatedWord, dictionary);
        }
    }

    public static List<Word> lookUpWord(String word_expression, Dictionary dictionary) {
        int firstIndex = getFirstIndex(word_expression, dictionary);
        List<Word> wordList = new ArrayList<>();
        for (int i = firstIndex; i < dictionary.getWordList().size(); i++) {
            String iExpression = dictionary.getWordList().get(i).getExpression();
            if (!iExpression.equalsIgnoreCase(word_expression)) break;
            wordList.add(dictionary.getWordList().get(i));
        }
        return wordList;
    }

    /** import from file. */
    public static void insertFromFile(String file, Dictionary dictionary) throws SQLException {
        File wordsFile = new File(file);
        try {
            Scanner readFile = new Scanner(wordsFile);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] parts = line.split("|");
                addWordByOrder(new Word(parts[0], parts[1]), dictionary);
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
            for (Word word: dictionary.getWordList()) {
                myWriter.write(word.getExpression() + "|" + word.getMeaning() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully export to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred when exporting to file");
        }
    }

    public static List<String> getKeyList(Dictionary dictionary) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < dictionary.getWordList().size(); i++) {
            String expression = dictionary.getWordList().get(i).getExpression();
            res.add(expression);
            while (i < dictionary.getWordList().size()
                    && dictionary.getWordList().get(i).getExpression().equalsIgnoreCase(expression)) {
                i++;
            }
            i--;
        }
        return res;
    }

    public static void importFromDatabase(Dictionary dictionary) throws SQLException, ClassNotFoundException {
        List<Data> dataList = Database.selectFromTableOrderByWord(dictionary.getDbTable());
        for (Data data: dataList) {
            dictionary.getWordList().add(new Word(data.getId(),
                    data.getWord(), data.getDescription(), data.getHtml()));
        }
    }

    public static void insertIntoDatabase(Dictionary dictionary, Word word) throws SQLException, ClassNotFoundException {
        int id = dictionary.getWordList().size();
        Database.insertIntoTable(dictionary.getDbTable(), new Data(id, word.getExpression(),
                word.getHtml(), word.getMeaning()));
    }

    public static void updateDatabase(Dictionary dictionary, Word word) throws SQLException, ClassNotFoundException {
        Database.updateTable(dictionary.getDbTable(), new Data(word.getId(), word.getExpression(),
                word.getHtml(), word.getMeaning()));
    }

    public static void deleteFromDatabase(Dictionary dictionary, int wordId) throws SQLException, ClassNotFoundException {
        Database.deleteFromTable(dictionary.getDbTable(), wordId);
    }

    public static boolean checkInDict(String word, Dictionary dictionary) {
        int left = 0;
        int right = dictionary.getWordList().size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (word.equalsIgnoreCase(dictionary.getWordList().get(mid).getExpression())) {
                return true;
            } else if (word.compareToIgnoreCase(dictionary.getWordList()
                    .get(mid).getExpression()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static int getFirstIndex(String word, Dictionary dictionary) {
        int res = -1;
        int left = 0;
        int right = dictionary.getWordList().size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (word.equalsIgnoreCase(dictionary.getWordList().get(mid).getExpression())) {
                res = mid;
                right = mid - 1;
            } else if (word.compareToIgnoreCase(dictionary.getWordList()
                    .get(mid).getExpression()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static int getLastIndex(String word, Dictionary dictionary) {
        int res = -1;
        int left = 0;
        int right = dictionary.getWordList().size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (word.equalsIgnoreCase(dictionary.getWordList().get(mid).getExpression())) {
                res = mid;
                left = mid + 1;
            } else if (word.compareToIgnoreCase(dictionary.getWordList()
                    .get(mid).getExpression()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static int getIndex(String word, Dictionary dictionary) {
        int res = -1;
        int left = 0;
        int right = dictionary.getWordList().size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (word.equalsIgnoreCase(dictionary.getWordList().get(mid).getExpression())) {
                res = mid;
                left = mid + 1;
            } else if (word.compareToIgnoreCase(dictionary.getWordList()
                    .get(mid).getExpression()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (word.equalsIgnoreCase(dictionary.getWordList().get(left).getExpression())) {
            res = left;
        } else if (word.compareToIgnoreCase(dictionary.getWordList()
            .get(left).getExpression()) < 0) {
            res = left - 1;
        } else {
            res = left + 1;
        }
        return res;
    }

    public static int searchIndexById(int wordId, String wordExpression, Dictionary dictionary) {
        int index = getFirstIndex(wordExpression, dictionary);
        List<Word> wordList = dictionary.getWordList();
        while (wordList.get(index).getExpression().equalsIgnoreCase(wordExpression)) {
            if (wordList.get(index).getId() == wordId) {
                return index;
            }
            index++;
        }
        return index;
    }



}
