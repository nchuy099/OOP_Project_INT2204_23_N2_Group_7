package console;

public class Word {
    private String expression;
    private String meaning;
    private String html;
    private String ipa;

    public Word() {
        expression = "unknown";
        meaning = "unknown";
        html = "unknown";
        ipa = "unknown";
    }

    public Word(String expression, String meaning) {
        this.expression = expression;
        this.meaning = meaning;
        html = "unknown";
        ipa = "unknown";
    }

    public Word(String expression, String meaning,
                String html, String ipa) {
        this.expression = expression;
        this.meaning = meaning;
        this.html = html;
        this.ipa = ipa;
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public String getWordInfo() {
        return meaning;
    }
}