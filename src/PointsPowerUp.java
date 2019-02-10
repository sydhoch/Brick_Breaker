

public class PointsPowerUp extends PowerUp{

    private static final String POINTS_POWERUP = "pointsPower.gif";

    public PointsPowerUp(){
        super();
    }

    @Override
    protected void setPowerUpImage() {
        setImage(POINTS_POWERUP);

    }

    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        player.setScoreIncrement(player.getScoreIncrement() * 2);
    }

    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            player.setScoreIncrement(player.getScoreIncrement() / 2);
        }
        setIsActive(false);
    }
}
