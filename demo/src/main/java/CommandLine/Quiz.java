package CommandLine;

import CommandLine.Word;
import Database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private final Random rand = new Random(System.currentTimeMillis());
    private final int DictionarySize = Database.getDictionaryManagement().getDictionary().getWordList().size();

    private String question;
    private String answer;
    private List<String> options;
    private boolean correct;

    public Quiz () {
        Word word = generateWord();
        question = word.getExpression();
        answer = word.getMeaning();
        options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            options.add(generateOption());
        }
        options.set(generateAnswerPos(), answer);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }


    public boolean isCorrect() {
        return correct;
    }


    public void checkCorrect(char playerChoice) {
        correct = options.get((int)playerChoice - 65).equals(answer);
    }

    public Word generateWord() {
        int quiz_no = rand.nextInt(DictionarySize);
        Word word = (Word) Database.getDictionaryManagement().
                getDictionary().getWordList().values().toArray()[quiz_no];
        return word;
    }

    public int generateAnswerPos() {
        return rand.nextInt(3);
    }

    public String generateOption() {
        int option_no = rand.nextInt(DictionarySize);
        Word word = (Word) Database.getDictionaryManagement().
                getDictionary().getWordList().values().toArray()[option_no];
        return word.getMeaning();
    }

}
