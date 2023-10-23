package meat.demo;

import Bone.Dictionary;
import Bone.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

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

    protected DictionaryManagement wordDataManagement;
    protected Dictionary wordData;

    protected DictionaryManagement dictionaryManagement() throws SQLException, ClassNotFoundException {
        DictionaryManagement newManagement = new DictionaryManagement();
        newManagement.importFromDatabase("dictionary.db");
        return newManagement;
    }

    protected DictionaryManagement bookmarkManagement() throws SQLException, ClassNotFoundException {
        DictionaryManagement newManagement = new DictionaryManagement();
        newManagement.insertFromFile("bookmark.txt");
        return newManagement;
    }

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
    @FXML
    public void showWordInfoView() {
        String input = searchField.getText();
        if (wordData.getWordList().containsKey(input)) {
            String wordInfo = wordData.getWordList().get(input).getWordInfo();
            wordInfoView.getEngine().loadContent(htmlEdit(wordInfo), "text/html");
        } else {
            wordInfoView.getEngine().loadContent("Word not found!");
        }
    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
