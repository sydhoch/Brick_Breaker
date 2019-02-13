import javafx.scene.Group;
import java.util.*;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Handles in-game interactions (such as collisions between )
 * Dependent on Ball, Brick, Paddle, Player, PowerUp classes
 *
 */

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


    /**
     * creates a new GameInteractions to check and handle collisions between items
     *
     * @param root holds all onscreen images
     * @param ball player's ball
     * @param bricks list of all bricks
     * @param paddle in-game paddle for hitting ball
     * @param player object maintaining player-related information
     * @param powerUps list of all powerUps
     * @param screenSize size of screen
     */
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
     *
     * @param tester object to run tests in testing mode
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
     *
     * @param tester object to run tests in testing mode
     */
    public void checkBallBricksCollision(Tests tester) {
        for (List<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                if (brick.collidesWith(myBall)) {
                   updateGameOnBrickCollision(tester, brick);
                }
            }
        }
    }

    private void updateGameOnBrickCollision(Tests tester, Brick brick){
        if (brick.givesPoints()) {
            myPlayer.increaseScore(myPlayer.getScoreIncrement());
        }
        brick.decreaseHealth(tester);
        myBall.bounceOff(tester);
        if (brick.isDestroyed()) {
            brick.activateBrickAbility(myBall, myRoot, myPowerUps, myScreenSize, tester);
        }
    }

    /**
     * Activates a powerup's special abilities if it collides with the paddle
     */
    public void checkPowerUpPaddleCollision() {
        for (PowerUp powerUp : myPowerUps) {
            if (powerUp.collidesWith(myPaddle)) {
                powerUp.activate(myPaddle, myBall, myPlayer);
            }
        }
    }
}




