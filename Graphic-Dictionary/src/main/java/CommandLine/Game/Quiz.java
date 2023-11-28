package CommandLine.Game;

import Application.Dictionary.Bookmark;
import CommandLine.Dictionary.Dictionary;
import CommandLine.Dictionary.DictionaryManagement;
import CommandLine.Dictionary.Word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private static final Random rand = new Random(System.currentTimeMillis());
    private static DictionaryManagement bookmark = new DictionaryManagement();
    private static DictionaryManagement dictionary = new DictionaryManagement();
    private String question;
    private String answer;
    private List<String> options;

    private Word generateWord() {
        int quiz_no = rand.nextInt(dictionary.getDictionary().getWordList().size());
        return (Word) dictionary.getDictionary().getWordList().values().toArray()[quiz_no];
    }

    private List<String> generateOptions() {
        List<String> list = new ArrayList<>();
        int par = dictionary.getDictionary().getWordList().size();
        for (int i = 0; i < 4; i++) {
            int option_no = rand.nextInt(par);
            Word word = (Word) dictionary.getDictionary().getWordList().values().toArray()[option_no];
            list.add(word.getMeaning());
        }
        return list;
    }

    private int generateAnswerPos() {
        return rand.nextInt(4);
    }

    public Quiz() throws SQLException, ClassNotFoundException {
        dictionary.importFromDatabase("src/main/resources/Application/data/dict_hh.db");
        bookmark.insertFromFile("src/main/resources/Application/data/bookmark.txt");
        bookmark = Bookmark.getInstance();
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
