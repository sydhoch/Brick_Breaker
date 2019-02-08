public class BallSpeedPowerUpFactory extends PowerUpFactory{

    private Ball myBall;

    public BallSpeedPowerUpFactory(Ball ball){
        myBall = ball;
    }

    @Override
    public PowerUp create() {
        return new BallSpeedPowerUp(myBall);
    }
}
