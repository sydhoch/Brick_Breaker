/**
 * @author leahschwartz
 *
 * Represents a factory that only creates PointsPowerUps in order to support random creation of powerups
 * Inherits from abstract PowerUpFactory
 * Dependent on PointsPowerUp class
 */
public class PointsPowerUpFactory extends PowerUpFactory{

    /**
     * Creates a factory object that can only make PointsPowerUps
     */
    public PointsPowerUpFactory(){
    }

    /**
     * Makes a new PointsPowerUp
     * @return PointsPowerUp
     */
    @Override
    public PowerUp create() {
        return new PointsPowerUp();
    }
}
