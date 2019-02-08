public class PaddleSizePowerUpFactory extends PowerUpFactory{

    Paddle myPaddle;

    public PaddleSizePowerUpFactory(Paddle paddle){
        myPaddle = paddle;
    }

    @Override
    public PowerUp create(){
        return new PaddleSizePowerUp(myPaddle);
    }

}
