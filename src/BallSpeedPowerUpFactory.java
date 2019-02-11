public class BallSpeedPowerUpFactory extends PowerUpFactory{


    public BallSpeedPowerUpFactory(){
    }

    @Override
    public PowerUp create() {
        return new BallSpeedPowerUp();
    }
}
