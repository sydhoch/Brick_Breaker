/**
 * @author leahschwartz
 *
 * Represents a factory that only creates PointsPowerUps in order to support random creation of powerups
 */
public class PointsPowerUpFactory extends PowerUpFactory{

    public PointsPowerUpFactory(){
    }

    @Override
    public PowerUp create() {
        return new PointsPowerUp();
    }
}
