package application;

import console.Dictionary;
import console.DictionaryManagement;
import console.Word;
import console.VoiceRSS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    TextField searchField;
    @FXML
    ListView<String> searchListView;
    @FXML
    AnchorPane searchMainContent;
    @FXML
    AnchorPane searchFieldPane;
    @FXML
    AnchorPane definationPane;
    @FXML
    Button showDefinitionButton;
    @FXML
    Label inputWord;
    @FXML
    WebView wordInfoView;

    ObservableList<String> searchList = FXCollections.observableArrayList();

    Dictionary dictionary = new Dictionary();

    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void loadDictionaryData() throws SQLException, ClassNotFoundException {
        DictionaryManagement temp = new DictionaryManagement();
        temp.importFromDatabase("src/main/resources/application/data/dictionary.db");
        dictionaryManagement = temp;
        dictionary = dictionaryManagement.getDictionary();
    }

    @FXML
    public void getUserInput() {
        searchList.clear();
        String input = searchField.getText();
        if(input.isEmpty()) {
            searchList.clear();
            searchListView.setItems(searchList);
        }else {
            for (String word : dictionary.getWordList().keySet()) {
                if (word.length() >= input.length() && word.substring(0, input.length()).equals(input)) {
                    searchList.add(word);
                }
            }
            searchListView.setItems(searchList);
        }
    }

    public String htmlEdit(String WordInfo) {
        StringBuilder wordInfo = new StringBuilder(WordInfo);
        for (int i = 0; i < wordInfo.length() - 8; i++) {
            if (wordInfo.substring(i, i + 6).equals("<br />")) {
                wordInfo.replace(i, i + 7, "<br />&#160;<span style=\"color:#3498db\">");
                break;
            }
        }
        for (int i = 0; i < wordInfo.length(); i++) {
            if (wordInfo.substring(i, i + 6).equals("<br />")) {
                wordInfo.insert(i, "</span>");
                break;

            }
        }
        return wordInfo.toString();
    }

    public void showDefinition() {
        String input = searchField.getText();
        inputWord.setText(input);
        if (dictionary.getWordList().containsKey(input)) {
            Word temp = dictionary.getWordList().get(input);
            String wordInfo = temp.getMeaning();
            wordInfoView.getEngine().loadContent(htmlEdit(wordInfo), "text/html");
        } else {
            wordInfoView.getEngine().loadContent("404 Error: Word Not Found!");
        }
    }

    public void getSuggest() {

    }

    public void spellingSpeaker1() throws Exception {
        VoiceRSS.speakWord(inputWord.getText(), VoiceRSS.englishUK);
    }

    public void spellingSpeaker2() throws Exception {
        VoiceRSS.speakWord(inputWord.getText(), VoiceRSS.englishUS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
