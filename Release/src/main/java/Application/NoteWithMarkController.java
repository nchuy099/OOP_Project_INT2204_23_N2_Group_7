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

public class NoteWithMarkController extends NoteController{
    @FXML
    protected Button markButton;
    public void markNote() throws SQLException, ClassNotFoundException {
        DictionaryManagement.addWordByOrder(wordNote, Bookmark.getInstance());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Marked!");
        alert.showAndWait();
    }

    @Override
    public void removeNote() throws SQLException, ClassNotFoundException, IOException {
        DictionaryManagement.removeWord(wordNote.getId(),
                wordNote.getExpression(), Search.getInstance());
        if (DictionaryManagement.checkInDict(wordNote.getExpression(),
                Bookmark.getInstance())) {
            MainMenuController.searchController.showWordLayout(wordNote.getExpression());
        } else {
            MainMenuController.searchController.reset();
        }
    }

}
