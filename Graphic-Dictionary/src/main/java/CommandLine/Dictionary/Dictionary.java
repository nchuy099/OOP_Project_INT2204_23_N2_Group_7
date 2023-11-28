package CommandLine.Dictionary;

import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary {
    private SortedMap<String, Word> wordList;

    public Dictionary() {
        wordList = new TreeMap<>();
    }

    public Dictionary(SortedMap<String, Word> wordList) {
        this.wordList = wordList;
    }

    public SortedMap<String, Word> getWordList() {
        return wordList;
    }

    public void setWordList(SortedMap<String, Word> wordList) {
        this.wordList = wordList;
    }

}
