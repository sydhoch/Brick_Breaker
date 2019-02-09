public class PaddleSizePowerUpFactory extends PowerUpFactory{

    public PaddleSizePowerUpFactory(){
    }

    @Override
    public PowerUp create(Ball ball, Paddle paddle, Player player){
        return new PaddleSizePowerUp(paddle);
    }

}
