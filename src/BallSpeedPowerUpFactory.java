public class BallSpeedPowerUpFactory extends PowerUpFactory{


    public BallSpeedPowerUpFactory(){
    }

    @Override
    public PowerUp create(Ball ball, Paddle paddle, Player player) {
        return new BallSpeedPowerUp(ball);
    }
}
