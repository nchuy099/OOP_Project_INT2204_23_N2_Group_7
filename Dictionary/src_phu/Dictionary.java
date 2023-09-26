import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {
    private List<Word> wordList;

    public Dictionary() {
        this.wordList = new ArrayList<Word>();
    }

    public void addNewWord(Word p) {
        this.wordList.add(p);

        Collections.sort(this.wordList, new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
                return word1.getWordTarget().compareToIgnoreCase(word2.getWordTarget());
            }
        });
    }

    public List<Word> getWordList() {
        return this.wordList;
    }
}