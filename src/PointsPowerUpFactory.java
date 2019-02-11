public class PointsPowerUpFactory extends PowerUpFactory{


    public PointsPowerUpFactory(){
    }

    @Override
    public PowerUp create() {
        return new PointsPowerUp();
    }
}
