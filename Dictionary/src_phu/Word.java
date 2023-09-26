public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
    }

    public void setWordTarget(String p) {
        this.wordTarget = p;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public void setWordExplain(String p) {
        this.wordExplain = p;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void showWord() {
        System.out.printf("%-4s | %-7s | %s\n", "Find", this.wordTarget, this.wordExplain);
    }
}