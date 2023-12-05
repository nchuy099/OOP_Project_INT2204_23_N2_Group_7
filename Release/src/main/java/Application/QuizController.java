package Application;

import Game.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuizController extends GameController implements Initializable {
    @FXML
    private Label question;

    @FXML
    private Button opt1, opt2, opt3, opt4;

    private Quiz quiz;

    static int maxQuiz;
    private int quizNo;
    static int correct;
    static int wrong;

    @FXML
    public void chooseOpt1(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (quiz.isCorrect('A')) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (quizNo == maxQuiz) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
            Scene scene = new Scene(loader.load());
            ResultController resultController = loader.getController();
            stage.setScene(scene);
            stage.show();
        } else {
            quizNo++;
            loadQuestion();
        }
    }

    @FXML
    public void chooseOpt2(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (quiz.isCorrect('B')) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (quizNo == maxQuiz) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
            Scene scene = new Scene(loader.load());
            ResultController resultController = loader.getController();
            stage.setScene(scene);
            stage.show();
        } else {
            quizNo++;
            loadQuestion();
        }
    }

    @FXML
    public void chooseOpt3(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (quiz.isCorrect('C')) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (quizNo == maxQuiz) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
            Scene scene = new Scene(loader.load());
            ResultController resultController = loader.getController();
            stage.setScene(scene);
            stage.show();
        } else {
            quizNo++;
            loadQuestion();
        }
    }

    @FXML
    public void chooseOpt4(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (quiz.isCorrect('D')) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (quizNo == maxQuiz) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/media/style/Main.css").toExternalForm());
            ResultController resultController = loader.getController();
            stage.setScene(scene);
            stage.show();
        } else {
            quizNo++;
            loadQuestion();
        }
    }

    public void setData(int questionsNumber) {
        maxQuiz = questionsNumber;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadQuestion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        quizNo = 1;
        correct = 0;
        wrong = 0;
    }

    public void loadQuestion() throws SQLException, ClassNotFoundException {
        quiz = new Quiz();
        question.setText(quiz.getQuestion());
        opt1.setText(quiz.getOptions().get(0));
        opt2.setText(quiz.getOptions().get(1));
        opt3.setText(quiz.getOptions().get(2));
        opt4.setText(quiz.getOptions().get(3));
    }
}
