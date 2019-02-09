public class PointsPowerUpFactory extends PowerUpFactory{


    public PointsPowerUpFactory(){
    }

    @Override
    public PowerUp create(Ball ball, Paddle paddle, Player player) {
        return new PointsPowerUp(player);
    }
}
