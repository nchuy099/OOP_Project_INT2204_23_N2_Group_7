package Application;

import DictionaryP.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditController {
    protected Word wordNote;
    @FXML
    protected TextField expression;
    @FXML
    protected TextField meaning;
    @FXML
    protected HTMLEditor html;
    protected WebView noteView;

    private Stage stage;

    @FXML
    public void saveWord() throws SQLException, ClassNotFoundException {
        Word updatedWord = new Word(wordNote.getId(), expression.getText().trim(), meaning.getText().trim(),
                html.getHtmlText().trim());
        DictionaryManagement.updateWord(wordNote.getExpression(), updatedWord, Search.getInstance());
        MainMenuController.searchController.wordLabel.setText(expression.getText().trim());
        MainMenuController.searchController.setListView();
        stage.close();
        noteView.getEngine().loadContent("" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body style=\"background-color: #f0ffff;\">\n"
                + updatedWord.getHtml() +
                "</body>\n" +
                "</html>","text/html");
    }

    @FXML
    public void cancelAction() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setData(Word wordNote, WebView noteView) {
        this.noteView = noteView;
        this.wordNote = wordNote;
        expression.setText(wordNote.getExpression());
        meaning.setText(wordNote.getMeaning());
        html.setHtmlText(wordNote.getHtml());
    }

}
