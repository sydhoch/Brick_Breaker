public class PointsPowerUpFactory extends PowerUpFactory{

    private Player myPlayer;

    public PointsPowerUpFactory(Player player){
        myPlayer = player;
    }

    @Override
    public PowerUp create() {
        return new PointsPowerUp(myPlayer);
    }
}
