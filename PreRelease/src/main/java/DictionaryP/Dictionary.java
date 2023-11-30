package DictionaryP;

import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary {
    private SortedMap<String, Word> wordList;
    private String dbTable;

    public Dictionary() {
        wordList = new TreeMap<>();
        dbTable = "unknown";
    }

    public Dictionary(SortedMap<String, Word> wordList) {
        this.wordList = wordList;
        dbTable = "unknown";
    }

    public Dictionary(String dbTable) {
        wordList = new TreeMap<>();
        this.dbTable = dbTable;
    }

    public SortedMap<String, Word> getWordList() {
        return wordList;
    }

    public void setWordList(SortedMap<String, Word> wordList) {
        this.wordList = wordList;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }
}
