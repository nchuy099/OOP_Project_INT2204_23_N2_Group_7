package Application;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private ImageView ball;
    @FXML
    private ImageView gk;
    private Image gk_prepare1;
    private Image gk_prepare2;
    private Image gk_mid_save;
    private Image gk_low_right_save;
    private Image gk_low_left_save;
    private Image gk_high_right_save;
    private Image gk_high_left_save;

    @FXML
    public void gkSaveLowLeft() {
        TranslateTransition ballTransition = new TranslateTransition();
        ballTransition.setDuration(Duration.millis(1000));
        ballTransition.setNode(ball);
        ballTransition.setByX(-190);
        ballTransition.setByY(-120);
        ScaleTransition ballScale = new ScaleTransition();
        ballScale.setDuration(Duration.millis(500));
        ballScale.setNode(ball);
        ballScale.setToX(-1);
        ballScale.setToY(-1);
        gk.setImage(gk_low_left_save);
        gk.setX(-30);
        TranslateTransition gkTransition = new TranslateTransition();
        gkTransition.setDuration(Duration.millis(1000));
        gkTransition.setNode(gk);
        gkTransition.setByX(-150);
        gkTransition.setByY(40);
        ParallelTransition parallelTransition = new ParallelTransition(
                ballTransition, ballScale, gkTransition);
        parallelTransition.play();
    }

    public void gkPrepare() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(3);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            gk.setImage(gk_prepare1);
            gk.setX(50 - gk.getImage().getWidth() / 2);
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> {
            gk.setImage(gk_prepare2);
            gk.setX(50 - gk.getImage().getWidth() / 2);
        }));
        timeline.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gk_prepare1 = new Image(new File("src/main/resources/media/img/gk_prepare1.png")
                .toURI().toString());
        gk_prepare2 = new Image(new File("src/main/resources/media/img/gk_prepare2.png")
                .toURI().toString());
        gk_low_left_save = new Image(new File("src/main/resources/media/img/gk_low_left_save.png")
                .toURI().toString());
        gk_low_right_save = new Image(new File("src/main/resources/media/img/gk_low_right_save.png")
                .toURI().toString());
        gk_high_left_save = new Image(new File("src/main/resources/media/img/gk_high_left_save.png")
                .toURI().toString());
        gk_high_right_save = new Image(new File("src/main/resources/media/img/gk_high_right_save.png")
                .toURI().toString());
        gkPrepare();
    }
}
