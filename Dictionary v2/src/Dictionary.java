
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary {
  private static final SortedMap<String, Word> wordList = new TreeMap<>();

  /** AddWord1. */
  public static void addWord(Word word) {
    wordList.put(word.getExpression(), word);
  }

  /** AddWord2. */
  public static void addWord(String word_expression, String word_meaning) {
    Word newWord = new Word();
    newWord.setExpression(word_expression);
    newWord.setMeaning(word_meaning);
    wordList.put(newWord.getExpression(), newWord);
  }

  public static Map<String, Word> getWordList() {
    return wordList;
  }
}
