public class BallSpeedPowerUp extends PowerUp{

    private Ball myBall;
    private static final String BALL_SPEED_POWERUP = "ballSpeedPower.gif";


    public BallSpeedPowerUp(Ball ball){
        super();
        myBall = ball;
    }

    @Override
    protected void setPowerUpImage() {
        setImage(BALL_SPEED_POWERUP);

    }

    @Override
    protected void doPower() {
//        System.out.println(myBall.getXVelocity());
//        System.out.println(myBall.getYVelocity());
        myBall.setXVelocity(myBall.getXVelocity() / 2);
        myBall.setYVelocity(myBall.getYVelocity() / 2);
//        System.out.println(myBall.getXVelocity());
//        System.out.println(myBall.getYVelocity());
    }

    @Override
    protected void deactivate() {
        myBall.setXVelocity(myBall.getXVelocity() * 2);
        myBall.setYVelocity(myBall.getYVelocity() * 2);
    }
}
