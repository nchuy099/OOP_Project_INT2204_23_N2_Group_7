package Application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FlashcardResultController implements Initializable {
    @FXML
    private Label remark, marks, marksText, correctText, wrongText;

    @FXML
    private ProgressIndicator correctProgress, wrongProgress;

    int correct;
    int wrong;

    int number;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        correct = QuizController.correct;
        wrong = QuizController.wrong;
        number = QuizController.maxQuiz;

        correctText.setText("Correct Answers : " + correct);
        wrongText.setText("Incorrect Answers : " + wrong);

        marks.setText(correct + "/" + number);
        double correctProg = (double) correct/number;
        correctProgress.setProgress(correctProg);

        double wrongProg = (double) wrong/number;
        wrongProgress.setProgress(wrongProg);


        marksText.setText("Scored: " + correct);

        if (correct<2) {
            remark.setText("Oh no..! You have failed the quiz. It seems that you need to " +
                    "improve your general knowledge. Practice daily! Check your results here.");
        } else if (correct>=2 && correct<5) {
            remark.setText("Oops..! You have scored less marks. It seems like you need to " +
                    "improve your general knowledge. Check your results here.");
        } else if (correct>=5 && correct<=7) {
            remark.setText("Good. A bit more improvement might help you to get better results. " +
                    "Practice is the key to success. Check your results here.");
        } else if (correct==8 || correct==9) {
            remark.setText("Congratulations! Its your hardwork and determination which helped you to " +
                    "score good marks. Check you results here.");
        } else if (correct==10) {
            remark.setText("Congratulations! You have passed the quiz with full marks because of your " +
                    "hard work and dedication towards studies. Keep it up! Check your results here.");
        }
    }
}
