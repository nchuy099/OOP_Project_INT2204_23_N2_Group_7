package CommandLine.Dictionary;

public class Word {
    private String expression = "unknown";
    private String meaning = "unknown";

    public Word() {
        expression = "unknown";
        meaning = "unknown";
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

}