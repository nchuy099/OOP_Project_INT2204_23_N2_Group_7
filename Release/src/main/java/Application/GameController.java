package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController {
    @FXML
    private TextField questionsNumber;

    @FXML
    private Button startButton;

    public void startGame (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(loader.load());
        QuizController quizController = loader.getController();
        try {
            quizController.setData(Integer.parseInt(questionsNumber.getText().trim()));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Nhập số từ bạn muốn luyện tập!");
            alert.showAndWait();
            return;
        }
        stage.setScene(scene);
        stage.show();
    }
}
