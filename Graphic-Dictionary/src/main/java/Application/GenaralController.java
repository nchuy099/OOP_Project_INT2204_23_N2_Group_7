package Application;

import CommandLine.Dictionary.DictionaryManagement;
import CommandLine.Dictionary.VoiceRSS;

import CommandLine.Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class GenaralController extends MainMenuController implements Initializable {
    @FXML
    protected TextField searchField;
    @FXML
    protected ListView<String> searchListView;
    @FXML
    protected Label wordLabel;
    @FXML
    protected WebView wordInfoView;
    @FXML
    protected HTMLEditor editor;
    @FXML
    protected Button addButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected Button speaker1;
    @FXML
    protected Button speaker2;

    protected ObservableList<String> searchList = FXCollections.observableArrayList();
    //protected ObservableList<String> bookMarkSearchList = FXCollections.observableArrayList();

    protected DictionaryManagement dictionary = new DictionaryManagement();
    //protected static Dictionary bookmarkList = new Dictionary();
    protected static DictionaryManagement bookmarkList = new DictionaryManagement();

    @FXML
    public void addToBookmark(Word word) {
        System.out.println("saved");
        bookmarkList.addWord(word);
    }

    public void spellingSpeaker1() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUK);
    }

    public void spellingSpeaker2() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUS);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
