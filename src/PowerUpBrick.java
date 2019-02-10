import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PowerUpBrick extends Brick{

    private static final String POWERUP_BRICK_IMAGE = "powerBrick.gif";
    private static final int STARTING_HEALTH = 1;
    private ArrayList<PowerUpFactory> myPowerUpFactories;


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
    public void activateBrickAbility(Ball ball, Group root, ArrayList<PowerUp> powerUps,
                                     int screenSize) {
        PowerUp myPowerUp = createRandomPowerUp();
        powerUps.add(myPowerUp);
        root.getChildren().add(myPowerUp.getImage());
        myPowerUp.placeItem(getXCoordinate(), getYCoordinate());
        myPowerUp.startFalling();
    }

    private PowerUp createRandomPowerUp(){
        Collections.shuffle(myPowerUpFactories);
        return myPowerUpFactories.get(0).create();

    }
}
