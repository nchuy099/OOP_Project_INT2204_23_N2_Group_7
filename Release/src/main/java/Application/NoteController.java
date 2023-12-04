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
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NoteController {
    @FXML
    private AnchorPane pane;
    @FXML
    protected WebView wordView;
    @FXML
    private Button markButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    private FXMLLoader editLoader;
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

    public void markNote() throws SQLException, ClassNotFoundException {
        DictionaryManagement.addWordInTail(wordNote, Bookmark.getInstance());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Marked!");
        alert.showAndWait();
    }

    public void showEditWindow() throws IOException {
        FXMLLoader loader = editLoader;
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        EditController editController = loader.getController();
        editController.stage = stage;
        editController.setData(wordNote);
        stage.setTitle("Edit");
        stage.setScene(scene);
        stage.show();
    }

    public void removeNote() throws SQLException, ClassNotFoundException, IOException {
        DictionaryManagement.removeWord(wordNote.getId(),
                wordNote.getExpression(), Search.getInstance());
        MainMenuController.searchController.showWordLayout(wordNote.getExpression());
    }
}
