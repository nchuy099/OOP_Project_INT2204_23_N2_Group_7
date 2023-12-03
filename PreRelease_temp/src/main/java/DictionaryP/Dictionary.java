package DictionaryP;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> wordList;
    private String dbTable;

    public Dictionary() {
        wordList = new ArrayList<>();
        dbTable = "unknown";
    }

    public Dictionary(List<Word> wordList) {
        this.wordList = wordList;
        dbTable = "unknown";
    }

    public Dictionary(String dbTable) {
        wordList = new ArrayList<>();
        this.dbTable = dbTable;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }
}
