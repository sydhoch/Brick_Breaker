import javafx.animation.Timeline;
import javafx.scene.Group;
import java.util.List;

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
                                     int screenSize, Tests tester, Timeline animation) {
        if(tester!=null){
            tester.setOriginalBallLocation(ball.getXCoordinate(),ball.getYCoordinate());
            ball.teleport(screenSize);
            tester.setNewBallLocation(ball.getXCoordinate(),ball.getYCoordinate());
            animation.stop();
            tester.testBallTransport();
        }
        else{
            ball.teleport(screenSize);
        }
    }
}
