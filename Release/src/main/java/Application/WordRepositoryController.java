package Application;

import DictionaryP.*;
import API.VoiceRSS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class WordRepositoryController implements Initializable {
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

    public void reset() throws SQLException, IOException, ClassNotFoundException {
        // clear all info
        obWordList.clear();
        searchField.clear();
        listView.getItems().clear();
        // init components
        wordLayout.getChildren().clear();
        wordLabel.setText("Word");
        // set listView
        obWordList.addAll(DictionaryManagement.getKeyList(dictionary));
        listView.setItems(obWordList);
    }

/*    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Word Not Found!");
        alert.showAndWait();
    }*/

    @FXML
    public void setListView() {
        obWordList.clear();
        List<String> wordList = DictionaryManagement.getKeyList(dictionary);
        String input = searchField.getText().trim();
        if(!input.isEmpty()) {
            for (String word : wordList) {
                if (word.length() >= input.length()
                        && word.substring(0, input.length()).equalsIgnoreCase(input)) {
                    obWordList.add(word);
                }
            }
        }
        listView.setItems(obWordList);
    }

    public void showWordLayout(String input) throws IOException, SQLException, ClassNotFoundException {
        if (!DictionaryManagement.checkInDict(input, dictionary)) {
            wordLabel.setText("Word not found!");
            wordLayout.getChildren().clear();
            return;
        }
        wordLayout.getChildren().clear();
        wordLabel.setText(input);
        List<Word> wordList = DictionaryManagement.lookUpWord(input, dictionary);
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
        if (listView.getSelectionModel().getSelectedItem() == null) return;
        String input = listView.getSelectionModel().getSelectedItem();
        showWordLayout(input);
    }

    public void spellingSpeaker1() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUS);
    }

    public void spellingSpeaker2() throws Exception {
        VoiceRSS.speakWord(wordLabel.getText(), VoiceRSS.englishUK);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionary = Bookmark.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            reset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
