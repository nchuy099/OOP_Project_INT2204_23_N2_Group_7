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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    protected VBox wordLayout;
    protected Dictionary dictionary;

    public void reset() {
        // clear all info
        obWordList.clear();
        searchField.clear();
        listView.getItems().clear();
        // init components
        wordLayout.getChildren().clear();
        wordLabel.setText("Word");
        // set listView
//        obWordList.addAll(DictionaryManagement.getKeyList(dictionary));
//        listView.setItems(obWordList);
    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("No Word Selection!");
        alert.showAndWait();
    }

    @FXML
    public void setListView() {
        obWordList.clear();
        List<String> wordList = DictionaryManagement.getKeyList(dictionary);
        String input = searchField.getText().trim();
        for (String word: wordList) {
            if (word.length() >= input.length()
                    && word.substring(0, input.length()).equals(input)) {
                obWordList.add(word);
            }
        }
        listView.setItems(obWordList);
    }

    public void showWordLayout(String input) throws IOException, SQLException, ClassNotFoundException {
        if (input.isEmpty()) {
            showWarningAlert();
            return;
        }
        wordLayout.getChildren().clear();
        wordLabel.setText(input);
        List<Word> wordList = DictionaryManagement.lookUpWord(input, Search.getInstance());
        for (int i = 0; i < wordList.size(); i++) {
            FXMLLoader noteLoader = new FXMLLoader(getClass().getResource("Note.fxml"));
            AnchorPane notePane = noteLoader.load();
            NoteController noteController = noteLoader.getController();
            noteController.setData(wordList.get(i));
            wordLayout.getChildren().add(notePane);
        }
    }

    @FXML
    public void showWordLayoutByClickLookUpButton() throws IOException, SQLException, ClassNotFoundException {
        String input = searchField.getText().trim();
        showWordLayout(input);
    }

    @FXML
    public void showWordLayoutByClickListView() throws IOException, SQLException, ClassNotFoundException {
        String input = listView.getSelectionModel().getSelectedItem();
        showWordLayout(input);
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
