/**
 * @author leahschwartz
 *
 * Represents a factory that only creates BallSpeedPowerUps in order to support random creation of powerups
 * Inherits from abstract PowerUpFactory class
 * Dependent on BallSpeedPowerUp class
 */

public class BallSpeedPowerUpFactory extends PowerUpFactory{

    /**
     * Creates a factory object that can only make BallSpeedPowerUps
     */
    public BallSpeedPowerUpFactory(){
    }

    /**
     * Makes a new BallSpeedPowerUp
     * @return BallSpeedPowerUp
     */
    @Override
    public PowerUp create() {
        return new BallSpeedPowerUp();
    }
}
