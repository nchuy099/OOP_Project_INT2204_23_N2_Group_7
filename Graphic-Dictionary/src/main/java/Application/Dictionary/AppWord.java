package Application.Dictionary;

import CommandLine.Word;

public class AppWord extends Word {
    private String ipa;
    private String html;

    public AppWord() {
        super();
        ipa = "unknown";
        html = "unknown";
    }

    public AppWord(String ipa, String html) {
        super();
        this.ipa = ipa;
        this.html = html;
    }

    public AppWord(String expression, String meaning,
                String html, String ipa) {
        super(expression, meaning);
        this.html = html;
        this.ipa = ipa;
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
}
