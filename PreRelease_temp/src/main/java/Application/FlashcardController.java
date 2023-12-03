package Application;

import API.VoiceRSS;
import DictionaryP.Bookmark;
import DictionaryP.Dictionary;
import DictionaryP.Search;
import DictionaryP.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class FlashcardController implements Initializable{
    private static final int Front = 1;
    private static final int Back = 0;
    protected int status;
    protected int currentNo;

    @FXML
    protected Button currentCard;
    protected Dictionary dictionary;

    public void flip() {
        if(status == Front) {
            setBackContent();
            status = Back;
        } else if (status == Back){
            setFrontContent();
            status = Front;
        }
    }

    public void speak() throws Exception {
        Word word = dictionary.getWordList().get(currentNo);
        if(status == Front) {
            VoiceRSS.speakWord(word.getExpression(), VoiceRSS.englishUS);
        } else if (status == Back){
            VoiceRSS.speakWord(word.getMeaning(), VoiceRSS.vietnamese);
        }
    }

    public void forwardNext() {
        if (currentNo < dictionary.getWordList().size() - 1) {
            currentNo++;
        }
        setFrontContent();
        status = Front;
    }

    public void backwardPrev() {
        if (currentNo > 0) {
            currentNo--;
        }
        setFrontContent();
        status = Front;
    }

    public void setFrontContent() {
        Word word = dictionary.getWordList().get(currentNo);
        currentCard.setText(word.getExpression());
    }

    public void setBackContent() {
        Word word = dictionary.getWordList().get(currentNo);
        currentCard.setText(word.getMeaning());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dictionary = Bookmark.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        currentNo = 0;
        status = Front;
    }
}
