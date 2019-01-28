
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.*;
import java.lang.*;


public class Paddle {

    public static final String PADDLE_IMAGE = "paddle.gif";
    private ImageView myPaddle;
    private static final int PADDLE_SPEED = 15;


    public Paddle(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myPaddle = new ImageView(image);

    }


    public void centerPaddle(int screenSize){
        myPaddle.setX(screenSize / 2 - myPaddle.getBoundsInLocal().getWidth() / 2);
        myPaddle.setY(screenSize - myPaddle.getBoundsInLocal().getHeight());
    }

    /**
     * Moves paddle when direction keys are pressed (only RIGHT and LEFT are handled)
     * @param code key pressed by user
     */
    public void handleSideKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
    }




    /**
     * Gets paddle image
     * @return paddle image as ImageView
     */
    public ImageView getView(){
        return myPaddle;
    }
}
