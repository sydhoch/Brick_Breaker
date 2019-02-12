/**
 * @author leahschwartz
 *
 * Represents a factory that only creates BallSpeedPowerUps in order to support random creation of powerups
 */

public class BallSpeedPowerUpFactory extends PowerUpFactory{


    public BallSpeedPowerUpFactory(){
    }

    @Override
    public PowerUp create() {
        return new BallSpeedPowerUp();
    }
}
