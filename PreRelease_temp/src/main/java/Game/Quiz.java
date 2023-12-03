package Game;

import DictionaryP.Dictionary;
import DictionaryP.Search;
import DictionaryP.Word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private static final Random rand = new Random(System.currentTimeMillis());
    private static Dictionary dictionary;

    private String question;
    private String answer;
    private List<String> options;

    private Word generateWord() {
        int quiz_no = rand.nextInt(dictionary.getWordList().size());
        return dictionary.getWordList().get(quiz_no);
    }

    private List<String> generateOptions() {
        List<String> list = new ArrayList<>();
        int temp = dictionary.getWordList().size();
        for (int i = 0; i < 4; i++) {
            int option_no = rand.nextInt(temp);
            Word word = dictionary.getWordList().get(option_no);
            list.add(word.getExpression());
        }
        return list;
    }

    private int generateAnswerPos() {
        return rand.nextInt(4);
    }

    public Quiz() throws SQLException, ClassNotFoundException {
        dictionary = Search.getInstance();
        Word word = generateWord();
        question = word.getMeaning();
        answer = word.getExpression();
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
