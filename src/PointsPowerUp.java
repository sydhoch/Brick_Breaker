

public class PointsPowerUp extends PowerUp{

    Player myPlayer;
    private static final String POINTS_POWERUP = "pointsPower.gif";

    public PointsPowerUp(Player player){
        super();
        myPlayer = player;
    }

    @Override
    protected void setPowerUpImage() {
        setImage(POINTS_POWERUP);

    }

    @Override
    protected void doPower() {
        myPlayer.setScoreIncrement(myPlayer.getScoreIncrement() * 2);
    }

    @Override
    public void undoPower() {
        myPlayer.setScoreIncrement(myPlayer.getScoreIncrement() / 2);
    }
}
