package Application;

import CommandLine.Dictionary;
import CommandLine.DictionaryManagement;
import CommandLine.VoiceRSS;
import CommandLine.Word;
import Database.Database;
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

import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GeneralSearchController extends MainMenuController implements Initializable {
    private String ID;
    protected ObservableList<String> wordList = FXCollections.observableArrayList();;
    @FXML
    protected ListView<String> wordListView;
    @FXML
    protected WebView wordInfoView;
    @FXML
    protected HTMLEditor htmlEditor;
    @FXML
    protected TextField searchField;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected Button speaker1;
    @FXML
    protected Button speaker2;
    @FXML
    protected Label wordLabel;
    @FXML
    protected Label ipaLabel;
    protected DictionaryManagement wordDataManagement;
    protected Dictionary wordData;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setWordListView() {
        wordList.clear();
        wordList.addAll(wordData.getWordList().keySet());
        wordListView.setItems(wordList);
    }

    @FXML
    public void handleSearchBar() {
        wordList.clear();
        String input = searchField.getText();
        for (String word: wordData.getWordList().keySet()) {
            if (word.length() >= input.length()
                    && word.substring(0, input.length()).equals(input)) {
                wordList.add(word);
            }
        }
        wordListView.setItems(wordList);
    }

    @FXML
    public void showWordInfoView() {
        String input = searchField.getText();
        if (wordData.getWordList().containsKey(input)) {
            Word word = wordData.getWordList().get(input);
            ipaLabel.setText("/" + word.getIpa() + "/");
            wordLabel.setText(word.getExpression());
            String wordHTML = word.getHtml();
            wordInfoView.getEngine().loadContent(wordHTML, "text/html");
        } else {
            wordInfoView.getEngine().loadContent("Word not found!");
        }
    }

    public void spellingSpeaker1() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUK);
    }

    public void spellingSpeaker2() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUS);
    }


    public void setData() {
        if (ID.equals(SEA)) {
            wordDataManagement = Database.getDictionaryManagement();
        } else if (ID.equals(BMK)){
            wordDataManagement = Database.getBookmarkManagement();
        }
        wordData = wordDataManagement.getDictionary();
        setWordListView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
