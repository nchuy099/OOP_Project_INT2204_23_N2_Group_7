package Application;

import CommandLine.Word;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class BookmarkController extends GenaralController implements Initializable {

    public void loadWordListData() {
//        bookmarkListManagement.insertFromFile("src/main/resources/application/data/wordList.txt");
//        bookmarkList =  bookmarkListManagement.getDictionary();
    }

    @FXML
    public void setListViewItem() {
        searchList.clear();
        String input = searchField.getText();
        if(input.isEmpty()) {
            searchList.clear();
            searchListView.setItems(searchList);
        }else {
            for (String word : bookmarkList.getDictionary().getWordList().keySet()) {
                if (word.length() >= input.length() && word.substring(0, input.length()).equals(input)) {
                    searchList.add(word);
                }
            }
            searchListView.setItems(searchList);
        }
    }

//    @FXML
    public void handleClickShowDefinitionButton() {
        String input = searchField.getText();
        wordLabel.setText(input);
        if (bookmarkList.getDictionary().getWordList().containsKey(input)) {
            Word temp = bookmarkList.getDictionary().getWordList().get(input);
            String wordHTML = temp.getHtml();
            wordInfoView.getEngine().loadContent(wordHTML, "text/html");
        } else {
            wordInfoView.getEngine().loadContent("404 Error: Word Not Found!");
        }
    }
    @FXML
    public void addToBookmark() {
        //bookmarkListManagement.addWord("null", "null");
        //super.addToBookmark("hello", "goodbye");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editor.setVisible(false);
    }

}
