import javafx.scene.Group;
import java.util.ArrayList;

public class PaddleSizePowerUp extends PowerUp {

    private Paddle myPaddle;
    private static final String PADDLE_SIZE_POWERUP = "paddleSizePower.gif";


    public PaddleSizePowerUp(Paddle paddle) {
        super();
        myPaddle = paddle;
    }

    @Override
    protected void setPowerUpImage(){
        setImage(PADDLE_SIZE_POWERUP);
    }

    @Override
    protected void doPower() {
        myPaddle.lengthen();
    }

    @Override
    protected void deactivate() {
        myPaddle.undoLengthen();
    }
}
