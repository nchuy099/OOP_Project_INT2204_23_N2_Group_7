package Application;

import Application.Game.Ball;
import Application.Game.GameEngine;
import Application.Game.Goalkeeper;
import CommandLine.Game.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameController extends MainMenuController implements Initializable {
    @FXML
    private ImageView gk;
    @FXML
    private ImageView ball;
    @FXML
    private Label question;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;
    @FXML
    private Button optionC;
    @FXML
    private Button optionD;
    private GameEngine gameEngine;
    private Quiz quiz;

    public void resetTurn() throws SQLException, ClassNotFoundException {
        quiz = new Quiz();
        question.setText(quiz.getQuestion());
        optionA.setText(quiz.getOptions().get(0));
        optionB.setText(quiz.getOptions().get(1));
        optionC.setText(quiz.getOptions().get(2));
        optionD.setText(quiz.getOptions().get(3));
    }

    @FXML
    public void checkGoalA() throws SQLException, ClassNotFoundException {
        if (quiz.isCorrect('A')) {
            gameEngine.scoreHighLeft();
        } else {
            gameEngine.highLeftSaved();
        }
        resetTurn();
    }

    @FXML
    public void checkGoalB() throws SQLException, ClassNotFoundException {
        if (quiz.isCorrect('B')) {
            gameEngine.scoreHighRight();
        } else {
            gameEngine.highRightSaved();
        }
        resetTurn();
    }

    @FXML
    public void checkGoalC() throws SQLException, ClassNotFoundException {
        if (quiz.isCorrect('C')) {
            gameEngine.scoreLowLeft();
        } else {
            gameEngine.lowLeftSaved();
        }
        resetTurn();
    }

    @FXML
    public void checkGoalD() throws SQLException, ClassNotFoundException {
        if (quiz.isCorrect('D')) {
            gameEngine.scoreLowRight();
        } else {
            gameEngine.lowRightSaved();
        }
        resetTurn();
    }

    @FXML
    public void testFunction() {
        gameEngine.testFunction();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameEngine = new GameEngine(new Goalkeeper(gk), new Ball(ball));
        gameEngine.gkPrepare();
        try {
            resetTurn();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
