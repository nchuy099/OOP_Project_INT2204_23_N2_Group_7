package Application;

import DictionaryP.Bookmark;
import DictionaryP.DictionaryManagement;
import DictionaryP.Search;
import DictionaryP.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NoteController {
    @FXML
    protected AnchorPane pane;
    @FXML
    protected WebView wordView;
    @FXML
    protected Button editButton;
    @FXML
    protected Button removeButton;
    protected FXMLLoader editLoader;
    protected Word wordNote;

    public void setData(Word word) {
        wordNote = word;
        wordView.getEngine().loadContent("" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body style=\"background-color: #ffffff;\">\n"
                + word.getHtml() +
                "</body>\n" +
                "</html>","text/html");
    }

    public void showEditWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        EditController editController = loader.getController();
        editController.stage = stage;
        editController.setData(wordNote, wordView);
        stage.setTitle("Edit");
        stage.setScene(scene);
        stage.show();
    }

    public void removeNote() throws SQLException, ClassNotFoundException, IOException {
        DictionaryManagement.removeWord(wordNote.getId(),
                wordNote.getExpression(), Search.getInstance());
        if (DictionaryManagement.checkInDict(wordNote.getExpression(),
                Search.getInstance())) {
            MainMenuController.searchController.showWordLayout(wordNote.getExpression());
        } else {
            MainMenuController.searchController.reset();
        }
    }
}
