package DictionaryP;

public class Word {
    private int id;
    private String expression;
    private String meaning;
    private String html;

    public Word() {
        id = -1;
        expression = "";
        meaning = "";
        html = "";
    }

    public Word(String expression, String meaning) {
        id = -1;
        this.expression = expression;
        this.meaning = meaning;
        html = "";
    }

    public Word(String expression, String meaning,
                   String html) {
        id = -1;
        this.expression = expression;
        this.meaning = meaning;
        this.html = html;
    }

    public Word(int id, String expression, String meaning,
                String html) {
        this.id = id;
        this.expression = expression;
        this.meaning = meaning;
        this.html = html;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}