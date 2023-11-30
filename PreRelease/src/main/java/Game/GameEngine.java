package Game;

import java.util.Random;

public class GameEngine {
    private static final Random rand = new Random(System.currentTimeMillis());
    private final Goalkeeper gk;
    private final Ball ball;

    public GameEngine(Goalkeeper gk, Ball ball) {
        this.gk = gk;
        this.ball = ball;
    }

    public void lowLeftSaved() {
        gk.gkSaveLowLeft();
        ball.ballOutLowLeft();
    }

    public void scoreLowLeft() {
        int i = rand.nextInt(3);
        if (i == 0) {
            gk.gkSaveHighLeft();
        } else if (i == 1) {
            gk.gkSaveHighRight();
        } else {
            gk.gkSaveLowRight();
        }
        ball.ballInLowLeft();
    }

    public void highLeftSaved() {
        gk.gkSaveHighLeft();
        ball.ballOutHighLeft();
    }

    public void scoreHighLeft() {
        int i = rand.nextInt(3);
        if (i == 0) {
            gk.gkSaveHighRight();
        } else if (i == 1) {
            gk.gkSaveLowLeft();
        } else {
            gk.gkSaveLowRight();
        }
        ball.ballInHighLeft();
    }

    public void lowRightSaved() {
        gk.gkSaveLowRight();
        ball.ballOutLowRight();
    }

    public void scoreLowRight() {
        int i = rand.nextInt(3);
        if (i == 0) {
            gk.gkSaveHighLeft();
        } else if (i == 1) {
            gk.gkSaveHighRight();
        } else {
            gk.gkSaveLowLeft();
        }
        ball.ballInLowRight();
    }

    public void highRightSaved() {
        gk.gkSaveHighRight();
        ball.ballOutHighRight();
    }

    public void scoreHighRight() {
        int i = rand.nextInt(3);
        if (i == 0) {
            gk.gkSaveHighLeft();
        } else if (i == 1) {
            gk.gkSaveLowLeft();
        } else {
            gk.gkSaveLowRight();
        }
        ball.ballInHighRight();
    }

    public void gkPrepare() {
        gk.gkPrepare();
    }

    public void testFunction() {
        gk.gkSaveHighRight();
    }
}
