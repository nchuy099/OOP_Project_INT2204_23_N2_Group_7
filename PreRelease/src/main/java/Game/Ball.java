package Game;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class Ball {
    private ImageView ball;
    private final PathTransition ballTransition = new PathTransition();

    public Ball(ImageView ball) {
        this.ball = ball;
    }

    public ImageView getBall() {
        return ball;
    }

    public void setBall(ImageView ball) {
        this.ball = ball;
    }

    public void ballInLowLeft() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX(), ball.getY(),
                ball.getX() - 180, ball.getY() - 120,
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1000));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });

        }
    }

    public void ballOutLowLeft() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX(), ball.getY(),
                ball.getX() - 165, ball.getY() - 75,
                ball.getX() - 250, ball.getY() + 90
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1400));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballInHighLeft() {
        /*ball.setX(16);
        ball.setY(15);*/
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX(), ball.getY(),
                ball.getX() - 180, ball.getY() - 210,
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1000));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballOutHighLeft() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX(), ball.getY(),
                ball.getX() - 155, ball.getY() - 200,
                ball.getX() - 350, ball.getY() - 190
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1400));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballInLowRight() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX() + 16, ball.getY() + 15,
                ball.getX() + 210, ball.getY() - 110,
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1000));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballOutLowRight() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX() + 16, ball.getY() + 15,
                ball.getX() + 210, ball.getY() - 60,
                ball.getX() + 170, ball.getY() + 90
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1400));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballInHighRight() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX() + 16, ball.getY() + 15,
                ball.getX() + 210, ball.getY() - 200,
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1300));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public void ballOutHighRight() {
        Polyline path = new Polyline();
        path.getPoints().addAll(new Double[] {
                ball.getX() + 16, ball.getY() + 15,
                ball.getX() + 155, ball.getY() - 220,
                ball.getX() + 400, ball.getY() - 280
        });
        if (ballTransition.getStatus() == Animation.Status.STOPPED) {
            ballTransition.setDuration(Duration.millis(1400));
            ballTransition.setNode(ball);
            ballTransition.setPath(path);
            ballTransition.play();
            ballTransition.setOnFinished(event -> {
                ball.setTranslateX(0);
                ball.setTranslateY(0);
            });
        }
    }

    public PathTransition getBallTransition() {
        return ballTransition;
    }

}
