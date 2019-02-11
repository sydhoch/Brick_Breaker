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
    private final String FAR_LEFT = "far left";
    private final String MIDDLE_LEFT = "middle left";
    private final String FAR_RIGHT = "far right";
    private final String MIDDLE_RIGHT = "middle right";


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
    public void checkBallHitsPaddle(){
        if(myBall.collidesWith(myPaddle)){
            if(myBall.getXCoordinate() < myPaddle.getCenter()){
                if(myBall.getXCoordinate()<myPaddle.getCenterOfLeftSide()){
                    myBall.bounceOffPad(FAR_LEFT);
                }
                else {
                    myBall.bounceOffPad(MIDDLE_LEFT);
                }
            }
            if(myBall.getXCoordinate() > myPaddle.getCenter()) {
                if(myBall.getXCoordinate()>myPaddle.getCenterOfRightSide()){
                    myBall.bounceOffPad(FAR_RIGHT);
                }
                else {
                    myBall.bounceOffPad(MIDDLE_RIGHT);
                }
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
        myBall.bounceOff();
        if (brick.isDestroyed()) {
            brick.activateBrickAbility(myBall, myRoot, myPowerUps, myScreenSize);
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




