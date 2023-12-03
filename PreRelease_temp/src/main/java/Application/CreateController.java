package Application;

import DictionaryP.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CreateController {
    @FXML
    protected TextField expression;
    @FXML
    protected TextField meaning;
    @FXML
    protected HTMLEditor html;

    Stage stage;

    @FXML
    public void createNewWord() throws SQLException, ClassNotFoundException {
        Dictionary dictionary = Search.getInstance();
        Word word = new Word(expression.getText().trim(), meaning.getText().trim(),
                html.getHtmlText().trim());
        DictionaryManagement.addWordByOrder(word, dictionary);
        MainMenuController.searchController.reset();
        stage.close();
    }

    @FXML
    public void cancelAction() {
        stage.close();
    }

}