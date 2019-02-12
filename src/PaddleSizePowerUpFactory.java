/**
 * @author leahschwartz
 *
 * Represents a factory that only creates PaddleSizePowerUp in order to support random creation of powerups
 */
public class PaddleSizePowerUpFactory extends PowerUpFactory{

    public PaddleSizePowerUpFactory(){
    }

    @Override
    public PowerUp create(){
        return new PaddleSizePowerUp();
    }

}
