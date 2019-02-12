import javafx.scene.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * @author leahschwartz
 *
 * Simulates a brick for ball to bounce off and destory
 * Upon destruction, drops a powerup
 */

public class PowerUpBrick extends Brick{

    private static final String POWERUP_BRICK_IMAGE = "powerBrick.gif";
    private static final int STARTING_HEALTH = 1;
    private List<PowerUpFactory> myPowerUpFactories;
    private final String POWERUP_RELEASED = "Powerup is Released";


    public PowerUpBrick(){
        super(STARTING_HEALTH);
        myPowerUpFactories = new ArrayList<>(Arrays.asList(new PaddleSizePowerUpFactory(), new PointsPowerUpFactory(),
                new BallSpeedPowerUpFactory()));
    }

    @Override
    public void setBrickImage(){
        setImage(POWERUP_BRICK_IMAGE);
    }

    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps,
                                     int screenSize, Tests tester) {
        PowerUp myPowerUp = createRandomPowerUp();
        powerUps.add(myPowerUp);
        root.getChildren().add(myPowerUp.getImage());
        myPowerUp.placeItem(getXCoordinate(), getYCoordinate());
        myPowerUp.startFalling();
        if(tester!=null){
            tester.setFirstEvent(POWERUP_RELEASED);
            tester.callTest();
        }
    }

    private PowerUp createRandomPowerUp(){
        Collections.shuffle(myPowerUpFactories);
        return myPowerUpFactories.get(0).create();

    }
}
