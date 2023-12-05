package Application;

import DictionaryP.Bookmark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameController {
    @FXML
    private TextField questionsNumber;

    @FXML
    private Button startButton;

    public void startGame (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (questionsNumber.getText().trim().isEmpty() || Integer.parseInt(questionsNumber.getText().trim())
                > Bookmark.getInstance().getWordList().size()) return;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(loader.load());
        QuizController quizController = loader.getController();
        quizController.setData(Integer.parseInt(questionsNumber.getText().trim()));
        stage.setScene(scene);
        stage.show();
    }
}
