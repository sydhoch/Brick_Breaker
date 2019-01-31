
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.lang.*;


public class Paddle extends Item{

    private static final String PADDLE_IMAGE = "paddle.gif";
    private static final int PADDLE_SPEED = 1000;


    public Paddle(){
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE)));
        setXVelocity(PADDLE_SPEED);
        setVisible(true);

    }

    /**
     * Moves paddle when direction keys are pressed (only RIGHT and LEFT are handled)
     * @param code key pressed by user
     */
    public void handleSideKeyInput(KeyCode code, double screenWidth, double elapsedTime){
        if (code == KeyCode.RIGHT && getImage().getBoundsInLocal().getMaxX() < screenWidth) {
            setXVelocity(Math.abs(getXVelocity()));
            move(elapsedTime);
        }
        else if (code == KeyCode.LEFT && getImage().getBoundsInLocal().getMinX() > 0) {
            setXVelocity(Math.abs(getXVelocity()) * -1);
            move(elapsedTime);
        }
    }

}
