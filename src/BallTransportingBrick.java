import javafx.scene.Group;
import java.util.List;
/**
 * @author leahschwartz
 *
 * Simulates a brick for ball to bounce off and destory
 * Upon destruction, teleports the ball to a random place on screen
 * Inherits from abstract Brick class
 */

public class BallTransportingBrick extends Brick{

    private static final String BALL_TRANSPORTING_BRICK_IMAGE = "ballTransportBrick.gif";
    private static final int STARTING_HEALTH = 1;

    /**
     * Creates a brick which takes 1 hit to destroy and has the ability to teleport the ball
     */
    public BallTransportingBrick(){
        super(STARTING_HEALTH);
    }

    @Override
    protected void setBrickImage(){
        setImage(BALL_TRANSPORTING_BRICK_IMAGE);
    }

    /**
     * Teleports ball to random place on screen
     *
     * @param ball player's ball
     * @param root holds all onscreen images
     * @param powerUps list of powerups in game
     * @param screenSize size of game screen
     * @param tester object to run tests in testing mode
     */
    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps,
                                     int screenSize, Tests tester) {
        if(tester!=null){
            tester.setOriginalBallLocation(ball.getXCoordinate(),ball.getYCoordinate());
            ball.teleport(screenSize);
            tester.setNewBallLocation(ball.getXCoordinate(),ball.getYCoordinate());
            tester.testBallTransport();
        }
        else{
            ball.teleport(screenSize);
        }
    }
}
