package console;

public class Word {
    private String expression;
    private String meaning;

    public Word() {

    }

    public Word(String expression, String meaning) {
        this.expression = expression;
        this.meaning = meaning;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getWordInfo() {
        return meaning;
    }
}