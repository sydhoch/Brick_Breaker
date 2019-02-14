/**
 * @author leahschwartz
 *
 * Represents a powerup that causes the ball to speed up to twice its current speed
 * Inherits from abstract PowerUp class
 * Dependent on Ball, Item, Paddle, Player, and PowerUp classes
 */
public class BallSpeedPowerUp extends PowerUp{

    private static final String BALL_SPEED_POWERUP = "ballSpeedPower.gif";

    /**
     * Creates a powerup with an image of a small round green powerup ball
     */
    public BallSpeedPowerUp(){
        super();
    }

    @Override
    protected void setPowerUpImage() {
        setImage(BALL_SPEED_POWERUP);

    }

    /**
     * "Activates" PowerUp's special effect by halving ball's X and Y velocities
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object maintaining player-related information
     */
    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        ball.setXVelocity(ball.getXVelocity() / 2);
        ball.setYVelocity(ball.getYVelocity() / 2);
    }

    /**
     * Undoes PowerUp's special effect by doubling ball's X and Y velocities
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object representing and maintaining player-related information
     */
    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            ball.setXVelocity(ball.getXVelocity() * 2);
            ball.setYVelocity(ball.getYVelocity() * 2);
        }
        setIsActive(false);
    }
}
