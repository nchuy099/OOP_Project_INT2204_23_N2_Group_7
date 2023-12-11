package Controllers;

import DictionaryP.Bookmark;
import DictionaryP.DictionaryManagement;
import DictionaryP.Search;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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
                Search.getInstance())) {
            MainMenuController.searchController.showWordLayout(wordNote.getExpression());
        } else {
            MainMenuController.searchController.reset();
        }
    }

}
