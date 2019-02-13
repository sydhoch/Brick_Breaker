/**
 * @author leahschwartz
 *
 * Represents a powerup that causes the paddle to double in size
 * Inherits from abstract PowerUp class
 */
public class PaddleSizePowerUp extends PowerUp {

    private static final String PADDLE_SIZE_POWERUP = "paddleSizePower.gif";

    /**
     * Creates a powerup with an image of a small round red powerup ball
     */
    public PaddleSizePowerUp() {
        super();
    }

    @Override
    protected void setPowerUpImage(){
        setImage(PADDLE_SIZE_POWERUP);
    }

    /**
     * "Activates" PowerUp's special effect by doubling paddle's length
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object maintaining player-related information
     */
    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        paddle.lengthen();
    }

    /**
     * Undoes PowerUp's special effect by halving paddle's length
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object representing and maintaining player-related information
     */
    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            paddle.undoLengthen();
        }
        setIsActive(false);
    }
}
