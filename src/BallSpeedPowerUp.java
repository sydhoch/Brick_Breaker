/**
 * @author leahschwartz
 *
 * Represents a powerup that causes the ball to speed up to twice its current speed
 */
public class BallSpeedPowerUp extends PowerUp{

    private static final String BALL_SPEED_POWERUP = "ballSpeedPower.gif";


    public BallSpeedPowerUp(){
        super();
    }

    @Override
    protected void setPowerUpImage() {
        setImage(BALL_SPEED_POWERUP);

    }

    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        ball.setXVelocity(ball.getXVelocity() / 2);
        ball.setYVelocity(ball.getYVelocity() / 2);
    }

    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            ball.setXVelocity(ball.getXVelocity() * 2);
            ball.setYVelocity(ball.getYVelocity() * 2);
        }
        setIsActive(false);
    }
}
