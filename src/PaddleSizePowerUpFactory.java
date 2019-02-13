/**
 * @author leahschwartz
 *
 * Represents a factory that only creates PaddleSizePowerUp in order to support random creation of powerups
 * Inherits from abstract PowerUpFactory class
 * Dependent on PaddleSizePowerUp class
 */
public class PaddleSizePowerUpFactory extends PowerUpFactory{

    /**
     * Creates a factory object that can only make PaddleSizePowerUps
     */
    public PaddleSizePowerUpFactory(){
    }

    /**
     * Makes a new PaddleSizePowerUp
     * @return PaddleSizePowerUp
     */
    @Override
    public PowerUp create(){
        return new PaddleSizePowerUp();
    }

}
