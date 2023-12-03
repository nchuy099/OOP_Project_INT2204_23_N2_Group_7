package Game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.io.File;

public class Goalkeeper {
    private final Image gk_prepare1  = new Image(new File("src/main/resources" +
            "/Application/game/gk_prepare1.png").toURI().toString());
    private final Image gk_prepare2 = new Image(new File("src/main/resources" +
            "/Application/game/gk_prepare2.png").toURI().toString());
    private final Image gk_low_left_save = new Image(new File("src/main/resources" +
            "/Application/game/gk_low_left_save.png").toURI().toString());
    private final Image gk_high_left_save = new Image(new File("src/main/resources" +
            "/Application/game/gk_high_left_save.png").toURI().toString());
    private final Image gk_low_right_save = new Image(new File("src/main/resources" +
            "/Application/game/gk_low_right_save.png").toURI().toString());
    private final Image gk_high_right_save = new Image(new File("src/main/resources" +
            "/Application/game/gk_high_right_save.png").toURI().toString());
    private final Timeline gkTimeline = new Timeline();
    private ImageView gk;
    private final PathTransition gkTransition = new PathTransition();

    public Goalkeeper(ImageView gk) {
        this.gk = gk;
    }

    public ImageView getGk() {
        return gk;
    }

    public void setGk(ImageView gk) {
        this.gk = gk;
    }

    public void gkPrepare() {
        gkTimeline.setCycleCount(Timeline.INDEFINITE);
        gkTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            gk.setX(gk_prepare2.getWidth() / 2 - gk_prepare1.getWidth() / 2);
            gk.setImage(gk_prepare1);
        }));
        gkTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> {
            gk.setX(0);
            gk.setImage(gk_prepare2);
        }));
        gkTimeline.play();
    }

    public void gkSaveLowLeft() {
        Animation.Status status = gkTimeline.getStatus();
        if (status == Animation.Status.RUNNING) {
            gkTimeline.stop();
        }
        gk.setImage(gk_low_left_save);
        /*gk.setY(70);*/
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                gk.getX(), gk.getY() + 70,
                gk.getX() - 150, gk.getY() + 100,
        });
        if (gkTransition.getStatus() == Animation.Status.STOPPED) {
            gkTransition.setDuration(Duration.millis(1600));
            gkTransition.setNode(gk);
            gkTransition.setPath(path);
            gkTransition.play();
            gkTransition.setOnFinished(event -> {
                gk.setImage(gk_prepare2);
                gk.setTranslateX(0);
                gk.setTranslateY(0);
                gkPrepare();
            });
        }
    }

    public void gkSaveHighLeft() {
        Animation.Status status = gkTimeline.getStatus();
        if (status == Animation.Status.RUNNING) {
            gkTimeline.stop();
        }
        gk.setImage(gk_high_left_save);
        /*gk.setX(0);
        gk.setY(40);*/
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                gk.getX(), gk.getY() + 40,
                gk.getX() - 180, gk.getY() + 30
                //gk.getX() + 250, gk.getY() + 20

        });
        if (gkTransition.getStatus() == Animation.Status.STOPPED) {
            gkTransition.setDuration(Duration.millis(1700));
            gkTransition.setNode(gk);
            gkTransition.setPath(path);
            gkTransition.play();
            gkTransition.setOnFinished(event -> {
                gk.setImage(gk_prepare2);
                gk.setTranslateX(0);
                gk.setTranslateY(0);
                gkPrepare();
            });
        }
    }

    public void gkSaveLowRight() {
        Animation.Status status = gkTimeline.getStatus();
        if (status == Animation.Status.RUNNING) {
            gkTimeline.stop();
        }
        gk.setImage(gk_low_right_save);
        /*gk.setX(100);
        gk.setY(80);*/
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                gk.getX() + 100, gk.getY() + 80,
                gk.getX() + 150 + 100, gk.getY() + 30 + 80,
        });
        if (gkTransition.getStatus() == Animation.Status.STOPPED) {
            gkTransition.setDuration(Duration.millis(1500));
            gkTransition.setNode(gk);
            gkTransition.setPath(path);
            gkTransition.play();
            gkTransition.setOnFinished(event -> {
                gk.setImage(gk_prepare2);
                gk.setTranslateX(0);
                gk.setTranslateY(0);
                gkPrepare();
            });
        }
    }

    public void gkSaveHighRight() {
        Animation.Status status = gkTimeline.getStatus();
        if (status == Animation.Status.RUNNING) {
            gkTimeline.stop();
        }
        gk.setImage(gk_high_right_save);
        /*gk.setX(100);
        gk.setY(40);*/
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                gk.getX() + 100, gk.getY() + 40,
                gk.getX() + 280, gk.getY() + 40
                //gk.getX() + 250, gk.getY() + 20

        });
        if (gkTransition.getStatus() == Animation.Status.STOPPED) {
            gkTransition.setDuration(Duration.millis(1500));
            gkTransition.setNode(gk);
            gkTransition.setPath(path);
            gkTransition.play();
            gkTransition.setOnFinished(event -> {
                gk.setImage(gk_prepare2);
                gk.setTranslateX(0);
                gk.setTranslateY(0);
                gkPrepare();
            });
        }
    }
}
