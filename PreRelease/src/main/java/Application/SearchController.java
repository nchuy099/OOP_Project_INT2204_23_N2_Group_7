package Application;

import DictionaryP.*;
import API.VoiceRSS;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    protected ObservableList<String> obWordList = FXCollections.observableArrayList();
    @FXML
    protected ListView<String> listView;
    @FXML
    protected TextField searchField;
    @FXML
    protected Label wordLabel;
    @FXML
    protected WebView wordInfoView;
    @FXML
    protected HTMLEditor editor;
    @FXML
    private Button lookUpButton;
    protected Dictionary dictionary;

    public void reset() {
        // clear all info
        obWordList.clear();
        searchField.clear();
        listView.getItems().clear();
        // init components
        wordInfoView.getEngine().loadContent("");
        wordLabel.setText("Word");
        editor.setVisible(false);
    }

    @FXML
    public void setListView() {
        obWordList.clear();
        String input = searchField.getText();
        if(input.isEmpty()) {
            obWordList.clear();
            listView.setItems(obWordList);
        }else {
            for (String word : dictionary.getWordList().keySet()) {
                if (word.length() >= input.length() && word.substring(0, input.length()).equals(input)) {
                    obWordList.add(word);
                }
            }
            listView.setItems(obWordList);
        }
    }

    public void showWordInfoView(String input) {
        editor.setVisible(false);
        if (dictionary.getWordList().containsKey(input)) {
            Word word = dictionary.getWordList().get(input);
            wordLabel.setText(word.getExpression());
            String wordHTML = word.getHtml();
            wordInfoView.getEngine().loadContent(wordHTML, "text/html");
        } else {
            wordInfoView.getEngine().loadContent("Word not found!");
        }
    }

    @FXML
    public void showWordByClickLookUpButton() {
        String input = searchField.getText();
        showWordInfoView(input);
    }

    @FXML
    public void showWordByClickListView() {
        String input = listView.getSelectionModel().getSelectedItem();
        showWordInfoView(input);
    }

    public void spellingSpeaker1() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUK);
    }

    public void spellingSpeaker2() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUS);
    }

    @FXML
    public void showCreatePane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        CreateController createController = loader.getController();
        createController.stage = stage;
        stage.setTitle("Create");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addToBookmark() throws SQLException, ClassNotFoundException {
//        Word word = dictionary.getWordList().get(wordLabel.getText());
//        DictionaryManagement.addWord(word, Bookmark.getInstance());
    }

    public void handleClickEditButton() {
        String htmlText = dictionary.getWordList().get(wordLabel.getText()).getHtml();
        editor.setHtmlText(htmlText);
        editor.setVisible(true);
    }

    public void handleClickSaveButton() throws SQLException, ClassNotFoundException {
        editor.setVisible(false);
        String newHtml = editor.getHtmlText().replace(" dir=\"ltr\"", "");
        Word word = dictionary.getWordList().get(wordLabel.getText());
        word.setHtml(newHtml);
        wordInfoView.getEngine().loadContent(newHtml, "text/html");
        DictionaryManagement.adjustWord(word, dictionary);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionary = Search.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        reset();
    }
}
