package Database;

public class Data {
    private int id;
    private String word;
    private String html;
    private String description;

    public Data(int id, String word, String html,
                String description) {
        this.id = id;
        this.word = word;
        this.html = html;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
