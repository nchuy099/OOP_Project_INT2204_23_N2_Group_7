package Game;

import DictionaryP.Search;
import DictionaryP.Word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private static final Random rand;
    private static Search bookmark;
    static {
        rand = new Random(System.currentTimeMillis());
        try {
            bookmark = Search.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String question;
    private String answer;
    private List<String> options;

    private Word generateWord() {
        int quiz_no = rand.nextInt(bookmark.getWordList().size());
        return (Word) bookmark.getWordList().values().toArray()[quiz_no];
    }

    private List<String> generateOptions() {
        List<String> list = new ArrayList<>();
        int par = bookmark.getWordList().size();
        for (int i = 0; i < 4; i++) {
            int option_no = rand.nextInt(par);
            Word word = (Word) bookmark.getWordList().values().toArray()[option_no];
            list.add(word.getMeaning());
        }
        return list;
    }

    private int generateAnswerPos() {
        return rand.nextInt(4);
    }

    public Quiz() throws SQLException, ClassNotFoundException {
        bookmark = Search.getInstance();
        Word word = generateWord();
        question = word.getExpression();
        answer = word.getMeaning();
        options = generateOptions();
        options.set(generateAnswerPos(), answer);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(char choice) {
        return options.get((int)choice - 65).equals(answer);
    }

}