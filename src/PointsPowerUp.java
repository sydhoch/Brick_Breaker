/**
 * @author leahschwartz
 *
 * Represents a powerup that causes the each hit to give out double the points
 * Inherits from abstract PowerUp class
 * Dependent on Ball, Item, Paddle, Player, PowerUp classes
 */
public class PointsPowerUp extends PowerUp{

    private static final String POINTS_POWERUP = "pointsPower.gif";


    /**
     * Creates a powerup with an image of a small round yellow powerup ball
     */
    public PointsPowerUp(){
        super();
    }

    @Override
    protected void setPowerUpImage() {
        setImage(POINTS_POWERUP);

    }

    /**
     * "Activates" PowerUp's special effect by doubling score increment so that player gets double the points each time
     * score goes up
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object maintaining player-related information
     */
    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        player.setScoreIncrement(player.getScoreIncrement() * 2);
    }

    /**
     * Undoes PowerUp's special effect by halving the score increment
     * @param paddle in-game paddle for hitting ball
     * @param ball in-game ball
     * @param player object representing and maintaining player-related information
     */
    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            player.setScoreIncrement(player.getScoreIncrement() / 2);
        }
        setIsActive(false);
    }
}
