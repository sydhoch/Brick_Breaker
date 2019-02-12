import javafx.scene.Group;

import java.util.*;

import javafx.animation.Timeline;

public class GameInteractions {

    private Group myRoot;
    private Ball myBall;
    private List<List<Brick>> myBricks;
    private Paddle myPaddle;
    private Player myPlayer;
    private List<PowerUp> myPowerUps;
    private int myScreenSize;
    private final String LEFT = "left";
    private final String RIGHT = "right";


    public GameInteractions(Group root, Ball ball, List<List<Brick>> bricks, Paddle paddle,
                            Player player, List<PowerUp> powerUps, int screenSize){
        myRoot = root;
        myBall = ball;
        myBricks = bricks;
        myPaddle = paddle;
        myPlayer = player;
        myPowerUps = powerUps;
        myScreenSize = screenSize;

    }

    /**
     * Bounces the ball off the paddle if they collide
     */
    public void checkBallHitsPaddle(Tests tester){
        if(myBall.collidesWith(myPaddle)){
            if(myBall.getXCoordinate() < myPaddle.getCenter()){
                myBall.bounceOffPad(LEFT, tester);
            }
            if(myBall.getXCoordinate() > myPaddle.getCenter()) {
                myBall.bounceOffPad(RIGHT, tester);
            }
        }
    }

    /**
     * Checks if a brick has been collided with by the ball and calls for the game to be updated by decreasing the
     * brick's health, bouncing the ball off, and possibly releasing powerups
     */
    public void checkBallBricksCollision(Tests tester, Timeline animation) {
        for (List<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                if (brick.collidesWith(myBall)) {
                   updateGameOnBrickCollision(tester, animation, brick);
                }
            }
        }
    }

    private void updateGameOnBrickCollision(Tests tester, Timeline animation, Brick brick){
        if (brick.givesPoints()) {
            myPlayer.increaseScore(myPlayer.getScoreIncrement());
        }
        brick.decreaseHealth(tester, animation);
        myBall.bounceOff(tester, animation);
        if (brick.isDestroyed()) {
            brick.activateBrickAbility(myBall, myRoot, myPowerUps, myScreenSize, tester, animation);
        }
    }

    /**
     * Activates a powerup if it collides with the paddle
     */
    public void checkPowerUpPaddleCollision() {
        for (PowerUp powerUp : myPowerUps) {
            if (powerUp.collidesWith(myPaddle)) {
                powerUp.activate(myPaddle, myBall, myPlayer);
            }
        }
    }
}




