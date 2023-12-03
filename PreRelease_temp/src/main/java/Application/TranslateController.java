package Application;

import API.GoogleTranslate;
import API.VoiceRSS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    @FXML
    protected TextArea inText;
    @FXML
    protected TextArea outText;
    @FXML
    protected Button mainFromButton;
    @FXML
    protected Button extraFromButton;
    @FXML
    protected Button mainToButton;
    @FXML
    protected Button extraToButton;
    @FXML
    protected Button fromSpeakButton;
    @FXML
    protected Button toSpeakButton;
    @FXML
    protected Button translateButton2;

    protected String langFrom = GoogleTranslate.english;
    protected String langTo = GoogleTranslate.vietnamese;
    protected String voiceLangFrom = VoiceRSS.englishUS;
    protected String voiceLangTo = VoiceRSS.vietnamese;

    @FXML
    public void translate() throws IOException {
        outText.setText(GoogleTranslate.translate(langFrom, langTo, inText.getText().trim()));
    }

    @FXML
    public void tranLang() {
        // change lang to and from
        String tmp = langTo;
        langTo = langFrom;
        langFrom = tmp;
        // change voice lang to and from
        tmp = voiceLangFrom;
        voiceLangFrom = voiceLangTo;
        voiceLangTo = tmp;
        // change FromButton text
        tmp = mainFromButton.getText().trim();
        mainFromButton.setText(extraFromButton.getText().trim());
        extraFromButton.setText(tmp);
        // change ToButton text
        tmp = mainToButton.getText().trim();
        mainToButton.setText(extraToButton.getText().trim());
        extraToButton.setText(tmp);
    }

    @FXML
    public void FromSpeak() throws Exception {
        VoiceRSS.speakWord(inText.getText().trim(), voiceLangFrom);
    }

    @FXML
    public void ToSpeak() throws Exception {
        VoiceRSS.speakWord(outText.getText().trim(), voiceLangTo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langFrom = GoogleTranslate.english;
        langTo = GoogleTranslate.vietnamese;
    }
}
