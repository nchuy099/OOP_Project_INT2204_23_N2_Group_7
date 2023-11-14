package Application;

import CommandLine.Word;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController extends GenaralController implements Initializable {

    public void loadDictionaryData() throws SQLException, ClassNotFoundException {
        dictionary = dictionary.importFromDatabase("src/main/resources/application/data/dict_hh.db");
    }

    @FXML
    public void setListViewItem() {
        searchList.clear();
        String input = searchField.getText();
        if(input.isEmpty()) {
            searchList.clear();
            searchListView.setItems(searchList);
        }else {
            for (String word : dictionary.getDictionary().getWordList().keySet()) {
                if (word.length() >= input.length() && word.substring(0, input.length()).equals(input)) {
                    searchList.add(word);
                }
            }
            searchListView.setItems(searchList);
        }
    }

    public void handleClickShowDefinitionButton() {
        String input = searchField.getText();
        wordLabel.setText(input);
        if (dictionary.getDictionary().getWordList().containsKey(input)) {
            Word temp = dictionary.getDictionary().getWordList().get(input);
            String wordHTML = temp.getHtml();
            wordInfoView.getEngine().loadContent(wordHTML, "text/html");
        } else {
            wordInfoView.getEngine().loadContent("404 Error: Word Not Found!");
        }
    }

    public void handleClickEditButton() {
        String inputWord = wordLabel.getText();
        String temp = dictionary.getDictionary().getWordList().get(inputWord).getHtml();
        editor.setHtmlText(temp);
        editor.setVisible(true);
    }

    public void handleClickSaveButton() {
        editor.setVisible(false);
        String inputWord = wordLabel.getText();
        String newMeaning = editor.getHtmlText().replace(" dir=\"ltr\"", "");
        dictionary.getDictionary().getWordList().get(wordLabel.getText()).setMeaning(newMeaning);
        wordInfoView.getEngine().loadContent(newMeaning, "text/html");
        dictionary.updateWord(inputWord, new Word(inputWord, "", newMeaning, ""));
    }

    @FXML
    public void addToBookmark() {
        String expression = wordLabel.getText();
        super.addToBookmark(dictionary.getDictionary().getWordList().get(expression));
    }

    public void getSuggest() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editor.setVisible(false);
        wordInfoView.getEngine().loadContent("Định Nghĩa");
    }
}
