import javafx.scene.Group;
import java.util.ArrayList;

public class PaddleSizePowerUp extends PowerUp {

    private static final String PADDLE_SIZE_POWERUP = "paddleSizePower.gif";


    public PaddleSizePowerUp() {
        super();
    }

    @Override
    protected void setPowerUpImage(){
        setImage(PADDLE_SIZE_POWERUP);
    }

    @Override
    public void activate(Paddle paddle, Ball ball, Player player) {
        super.activate(paddle, ball, player);
        paddle.lengthen();
    }

    @Override
    public void deactivate(Paddle paddle, Ball ball, Player player) {
        if (isActive()) {
            paddle.undoLengthen();
        }
        setIsActive(false);
    }
}
