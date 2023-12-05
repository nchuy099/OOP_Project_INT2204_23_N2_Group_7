package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML
    private Label remark, correctText, wrongText;

    @FXML
    private ProgressIndicator correctProgress;
    @FXML
    private Button replayButton;
    @FXML
    private Button mainMenuButton;

    int correct;
    int wrong;

    int number;

    @FXML
    public void replay(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToMainMenu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/media/style/Main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/media/style/Search.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/media/style/Translate.css").toExternalForm());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> Platform.exit());
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        correct = QuizController.correct;
        wrong = QuizController.wrong;
        number = QuizController.maxQuiz;

        correctText.setText(String.valueOf(correct));
        wrongText.setText(String.valueOf(wrong));

        double correctProg = (double) correct/number;
        correctProgress.setProgress(correctProg);

        if (correctProg< (double) 2 / 10) {
            remark.setText("Don't give up now! Trust the process.");
        } else if (correctProg>=(double)2/10 && correctProg<(double)5/10) {
            remark.setText("Be kind to yourself, and keep practicing!");
        } else if (correctProg>=(double)5/10 && correctProg<(double)8/10) {
            remark.setText("Don't worry, you'll bounce back!");
        } else if (correctProg>=(double)8/10 || correctProg<(double)9/10) {
            remark.setText("Keep it up! You’re on your way to acing your material.");
        } else if (correctProg>= (double) 9 /10) {
            remark.setText("Way to go! You really deserve that high score!");
        }
    }
}
