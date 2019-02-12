import javafx.scene.Group;
import java.util.List;
/**
 * @author leahschwartz
 *
 * Simulates a brick for ball to bounce off and destory
 * Upon destruction, teleports the ball to a random place on screen
 */

public class BallTransportingBrick extends Brick{

    private static final String BALL_TRANSPORTING_BRICK_IMAGE = "ballTransportBrick.gif";
    private static final int STARTING_HEALTH = 1;

    public BallTransportingBrick(){
        super(STARTING_HEALTH);
    }

    @Override
    protected void setBrickImage(){
        setImage(BALL_TRANSPORTING_BRICK_IMAGE);
    }

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
