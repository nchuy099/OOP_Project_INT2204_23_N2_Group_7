package Application;

import Application.Dictionary.AppDictionaryManagement;
import Application.Dictionary.AppWord;
import CommandLine.Dictionary.Dictionary;
import CommandLine.Dictionary.DictionaryManagement;
import Application.API.VoiceRSS;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class GeneralController extends MainMenuController implements Initializable {
    protected ObservableList<String> wordList = FXCollections.observableArrayList();
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
    protected AppDictionaryManagement wordDataManagement;

    public abstract void setData() throws SQLException, ClassNotFoundException;

    public void setWordListView() {
        wordList.clear();
        wordInfoView.getEngine().loadContent("");
        wordLabel.setText("Word");
        ipaLabel.setText("IPA");
        searchField.clear();
        wordListView.getItems().clear();
        wordList.addAll(wordDataManagement.getDictionary().getWordList().keySet());
        wordListView.setItems(wordList);
    }

    @FXML
    public void handleSearchBar() {
        wordList.clear();
        String input = searchField.getText();
<<<<<<< HEAD:demo/src/main/java/meat/demo/GeneralSearchController.java
        for (String word: wordData.getWordList().keySet()) {
=======
        for (String word: wordDataManagement.getDictionary().getWordList().keySet()) {
>>>>>>> 3965d5af196808ece79bcea4625a925bccab919f:demo/src/main/java/Application/GeneralController.java
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
        if (wordDataManagement.getDictionary().getWordList().containsKey(input)) {
            AppWord word = (AppWord) wordDataManagement.getDictionary().getWordList().get(input);
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

<<<<<<< HEAD:demo/src/main/java/meat/demo/GeneralSearchController.java

    public void setData() {
        try {
            if (ID.equals(SEA)) {
                wordDataManagement = dictionaryManagement();
            } else if (ID.equals(BMK)){
                wordDataManagement = bookmarkManagement();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        wordData = wordDataManagement.getDictionary();
        setWordListView();
    }

=======
>>>>>>> 3965d5af196808ece79bcea4625a925bccab919f:demo/src/main/java/Application/GeneralController.java
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
